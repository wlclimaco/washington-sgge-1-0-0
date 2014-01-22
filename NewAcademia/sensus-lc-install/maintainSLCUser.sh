#! /bin/sh

#
# Print the usage statement
#
usage()
{
    echo Usage: $0 add "|" update "|" encryptpassword
    echo Use this script to maintain users and passwords on the SLC database. 
    echo Only one command line argument is required: add, update or encryptpassword
    echo Any additional arguments will be prompted for.
}

#
# Execute SQL to get the maximum user-id value
#
getUserMaxId()
{
    maxuserid=`psql -A -t -d $databaseName -U $databaseUserId -c "select max(user_id) from users"`
    if [ $? != 0 ]; then
        echo "FATAL: Unable to execute select max from database." >> $errorFile
        return 1
    fi

    echo $maxuserid

    return 0
}

#
# Execute SQL to display a list of all users
#
displayUserList()
{
    echo user-id, user-name, first-name, last-name, tenant-id
    psql -d $databaseName -U $databaseUserId -t -c "select (user_id, username, first_name, last_name, tenant_id) from users"
    if [ $? != 0 ]; then
        echo "FATAL: Unable to execute select from database for user display." >> $errorFile
        return 1
    fi

    return 0
}

#
# Execute SQL to display a single user
#
displayUser()
{
    echo user-id, user-name, first-name, last-name, tenant-id
    psql -d $databaseName -U $databaseUserId -t -c "select (user_id, username, first_name, last_name, tenant_id) from users where user_id=$1 and tenant_id=$2"
    if [ $? != 0 ]; then
        echo "FATAL: Unable to execute select from database for user display." >> $errorFile
        return 1
    fi

    return 0
}

#
# Encrypt the password
#
encryptPassword()
{
    # First create the java client 
    createEncrypter $1 $2
    if [ $? != 0 ]; then
        return 1
    fi

    # Then execute the java client to do the actual encryption
    password=`java -classpath $springSecurityJar:/tmp EncryptSLCPassword $1 $2`
    if [ $? != 0 ]; then
        echo "FATAL: Unable to invoke Java to encrypt password." >> $errorFile
        return 1
    fi

    echo $password

    return 0
}

#
# Extract from the slc.war file the Spring Security jar required to encrypt.
#
extractSpringSecurityJar()
{
    slcWarFile=./slc.war
    if [ ! -f $slcWarFile ]; then
        echo ERROR: The $slcWarFile file was not found in the current directory. >> $errorFile
        return 1
    fi

    unzip -o -d /tmp $slcWarFile WEB-INF/lib/spring-security-core* >/dev/null
    if [ $? != 0 ]; then
        echo "FATAL: Unable to extract Spring Security Jar." >> $errorFile
        echo "       It is supposed to be in the $slcWarFile file." >> $errorFile
        return 1
    fi

    return 0
}

#
# Generate java code for security encrypter client
#
createEncrypter()
{
    # First extract the Sprint Security jar required for compilation.
    extractSpringSecurityJar
    if [ $? != 0 ]; then
        return 1
    fi

    springSecurityJar=`find /tmp/WEB-INF/lib -name '*.jar'`

    javaFileName=/tmp/EncryptSLCPassword.java

    cat > $javaFileName << EOF

    public class EncryptSLCPassword
    {
	public static void main(String[] args)
	{
		System.out.println( new org.springframework.security.authentication.encoding.ShaPasswordEncoder(256).encodePassword(args[1], args[0]));
	}
}

EOF

    # Now compile the java client.
    javac -cp $springSecurityJar $javaFileName
    if [ $? != 0 ]; then
        echo "ERROR: Unable to compile client." >> $errorFile
        echo "       Make sure javac is on the PATH" >> $errorFile
        return 1
    fi

    return 0
}

#
# Generate insert sql
#
#generateInsertSql $userNameToAdd $password $tenantIdToAdd $maxUserId
generateInsertSql()
{
    echo "INSERT INTO users( user_id, username, tenant_id, password, first_name, last_name, email, enabled, all_lights_auth, create_date, create_user )" >> $sqlFile
    echo "VALUES ( $4, '$1', $3, '$2', '$1', '$1', '$1@$1.org', TRUE, TRUE, CURRENT_TIMESTAMP, '$1' );" >> $sqlFile

    echo "INSERT INTO authorities( user_id, authority ) VALUES ( $maxUserId, 'ROLE_Role.Admin' );" >> $sqlFile
}

#
# Generate update sql
#
#    generateUpdateSql $userIdToUpdate $userNameToUpdate $tenantToUpdate $password
generateUpdateSql()
{
    echo "UPDATE users SET password='$4', modified_user='$2' WHERE user_id=$1 and tenant_id=$3;" >> $sqlFile
}

#
# Handle the update command
#
doUpdate()
{
    getDatabaseInfo

    read -p "Do you need to see a list of the current users to determine a valid user-id?[Y or N]" listUsers
    if [ $listUsers ]; then 
        if [ "$listUsers" != "n" ]; then
            displayUserList
            if [ $? != 0 ]; then
                abend
                exit 1
            fi
        fi
    else
        exit 0
    fi

    read -p "Enter user-id to update:" userIdToUpdate
    if [ ! $userIdToUpdate ]; then 
        exit
    fi
    read -p "Enter user-name to update:" userNameToUpdate
    if [ ! $userNameToUpdate ]; then 
        exit
    fi
    read -p "Enter tenant-id to update:]" tenantIdToUpdate
    if [ ! $tenantIdToUpdate ]; then 
        exit
    fi

    displayUser $userIdToUpdate $tenantIdToUpdate
    if [ $? != 0 ]; then
        abend
        exit 1
    fi
    
    read -p "Is this the correct user?[Y or N]" verify
    if [ "$verify" != "y" ]; then 
        exit
    fi

    if [ "$verify" != "n" ]; then
        read -p "Enter new password:" newPassword1
        if [ ! $newPassword1 ]; then 
            exit
        fi
        
        read -p "Enter new password again:" newPassword2
        if [ ! $newPassword2 ]; then 
            exit
        fi
        
        if [ "$newPassword1" != "$newPassword2" ]; then 
            echo Passwords do not match, try again.
            exit
        fi
    fi

    password=$(encryptPassword $userNameToUpdate $newPassword1)
    if [ $? != 0 ]; then
        abend
        exit 1
    fi

    sqlFile=/tmp/`basename $0`.$$.pgsql
    echo Sql file is $sqlFile

    generateUpdateSql $userIdToUpdate $userNameToUpdate $tenantIdToUpdate $password

    # Execute the script
    echo Executing update script
    psql -1 -d $databaseName -U $databaseUserId -f $sqlFile
    if [ $? != 0 ]; then
        echo ERROR: Problem with exection of sql script.
        exit 1
    fi
}

#
# Perform the add command
#
doAdd()
{
    getDatabaseInfo

    read -p "Enter user-name to add:" userNameToAdd
    if [ ! $userNameToAdd ]; then 
        exit
    fi
    read -p "Enter password for the new user:" userPasswordToAdd
    if [ ! $userPasswordToAdd ]; then 
        exit
    fi
    read -p "Enter tenant-id to add:]" tenantIdToAdd
    if [ ! $tenantIdToAdd ]; then 
        exit
    fi

    # Invoke function to get max user id value
    maxUserId=$(getUserMaxId)
    if [ $? != 0 ]; then
        abend
        exit 1
    fi

    # Bump the value to make sure it will work.
    (( maxUserId=$maxUserId + 3 ))

    # Invoke function to encrypt password
    password=$(encryptPassword $userNameToAdd $userPasswordToAdd)
    if [ $? != 0 ]; then
        abend
        exit 1
    fi

    sqlFile=/tmp/`basename $0`.$$.pgsql
    echo Sql file is $sqlFile

    # Generate the insert SQL
    generateInsertSql $userNameToAdd $password $tenantIdToAdd $maxUserId

    # Execute the script
    echo Executing update script
    psql -1 -d $databaseName -U $databaseUserId -f $sqlFile
    if [ $? != 0 ]; then
        echo ERROR: Problem with exection of sql script.
        exit 1
    fi
}

#
# Encrypt and display a user password
#
doEncryptPassword()
{
    read -p "Enter user-name:" userName
    if [ ! $userName ]; then
        exit
    fi
    read -p "Enter password to encrypt:" userPassword
    if [ ! $userPassword ]; then
        exit
    fi

    # Invoke function to encrypt password
    password=$(encryptPassword $userName $userPassword)
    if [ $? != 0 ]; then
        abend
        exit 1
    fi

    echo $password
}

#
# Prompt the user for database information
#
getDatabaseInfo()
{
    if [ ! $databaseName ] ; then
        read -p "Please enter the name of the database to use: " databaseName
        if [ ! $databaseName ] ; then
            exit 0
        fi
    fi
    if [ ! $databaseUserId ] ; then
        read -p "Please enter a valid user-id for the database: " databaseUserId
        if [ ! $databaseUserId ] ; then
            exit 0
        fi
    fi
    if [ ! $databasePassword ] ; then
        read -s -p "Please enter a valid password for this user-id: " databasePassword
        if [ ! $databasePassword ] ; then
            exit 0
        fi
    fi
    echo " "

    # This required in order for psql to work
    export PGPASSWORD=$databasePassword
}

#
# Handle errors by display error file
#
abend()
{
    echo
    echo An ERROR has occured
    cat $errorFile
}

####################################
# Main
####################################

echo " "
if [ $# = 0 ]; then
    usage
    exit 1
fi

# support a few positional command line arguments
if [ $# -gt 1 ]; then
    databaseName=$2
fi
if [ $# -gt 2 ]; then
    databaseUserId=$3
fi
if [ $# -gt 3 ]; then
    databasePassword=$4
fi

echo Starting...
echo Note, any blank input terminates this script.

errorFile=/tmp/`basename $0`.$$.errors

case "$1" in
    add)
        doAdd
    ;;
    update)
        doUpdate
    ;;
    encryptpassword)
        doEncryptPassword
    ;;
    *)
        echo "ERROR: Command line argument not recognized."
        usage
        exit 1
esac

echo Done.

exit 0
