#!/bin/bash

# Write a message to the screen and the log, regardless of the current echo configuration
shout () {
  if [[ "$1" == "-n" ]] ; then
    shift  # Eat the '-n' and make the second arg be the first arg.
    ECHO_CMD='echo -n -e'
  else
    ECHO_CMD='echo -e'
  fi

  $ECHO_CMD "$*"
}

# Ask the user specified yes/no question... and return yes (0) or no (1).
yesno() {
  prompt="$@"
  resp=X
  while true
  do
    shout -n "$prompt "
    read resp || return 1
    case $resp in
      y|Y|yes|YES|Yes) echo "Yes" >> $LOGFILE; return 0 ;;
      n|N|no|NO|No) echo "No" >> $LOGFILE; return 1 ;;
    esac
  done
}

# Verify that postgresql-server is not installed
rpm -qa | grep postgresql-server &> /dev/null
if [ $? -eq 0 ] ; then
  shout "### Postgresql Server is already installed and conflicts with new postgresql92 install"
  exit 1
fi

# Postgresql 9.1 parameters
#PG_RPM_NAME=postgresql91
#PG_RPM_NAME_SERVER=postgresql91-server
#PG_RPM_NAME_DEVEL=postgresql91-devel
#PG_RPM_NAME_CONTRIB=postgresql91-contrib
#PGDG_LATEST=pgdg-redhat91-9.1-5.noarch.rpm
#PG_SERVICE_NAME=postgresql-9.1
#PG_DIR=/var/lib/pgsql/9.1

# Postgresql 9.2 parameters
PG_RPM_NAME=postgresql92
PG_RPM_NAME_SERVER=postgresql92-server
PG_RPM_NAME_DEVEL=postgresql92-devel
PG_RPM_NAME_CONTRIB=postgresql92-contrib
PGDG_LATEST=pgdg-redhat92-9.2-7.noarch.rpm
PG_SERVICE_NAME=postgresql-9.2
PG_DIR=/var/lib/pgsql/9.2


#
# Check that we can install postgres libraries
#
yum search $PG_RPM_NAME | grep $PG_RPM_NAME
RC=$?
if [[ "$RC" == "1" ]]; then
  # Postgresql not found in repo, so warn user.
  echo "Warning: $PG_RPM_NAME is not found in the RHN repository configuration"

  if yesno "Would you like to use external servers to install $PG_RPM_NAME?" ; then
    # Use external postgresql version
    wget -O /tmp/$PGDG_LATEST  http://yum.postgresql.org/9.2/redhat/rhel-5.9-x86_64/$PGDG_LATEST
    rpm -i /tmp/$PGDG_LATEST
  else
    shout "### Failed to connect to RHN to install $PG_RPM_NAME"
    shout "### Reconfigure the server and rerun the install."
    exit 1
  fi
fi

shout "#### Installing postgres libraries"
yum install $PG_RPM_NAME $PG_RPM_NAME_SERVER $PG_RPM_NAME_DEVEL $PG_RPM_NAME_CONTRIB

shout "#### Initializing postgres db"
/sbin/service $PG_SERVICE_NAME initdb

shout "#### Patching postgres configuration"
runuser postgres -c "/usr/bin/patch -N -ub -i 9.2/patch.pg_hba.conf $PG_DIR/data/pg_hba.conf"
runuser postgres -c "/usr/bin/patch -N -ub -i 9.2/patch.postgresql.conf $PG_DIR/data/postgresql.conf"

shout "#### Starting postgres service"
/sbin/service $PG_SERVICE_NAME start
