
##if @BUILDER.connStringType=="mysql"##
$host="##@BUILDER.strConnectInfo1 s##";
$user="##@BUILDER.strConnectInfo2 s##";
$pwd="##@BUILDER.strConnectInfo3 s##";
$port="##@BUILDER.strConnectInfo4 s##";
$sys_dbname="##@BUILDER.strConnectInfo5 s##";

##elseif @BUILDER.connStringType=="mssql"##
##if @BUILDER.strConnectInfo3=="SSPI"##
$host="##@BUILDER.strConnectInfo1 s##";
$dbname="##@BUILDER.strConnectInfo2 s##";
$options="##@BUILDER.strConnectInfo3 s##";
$ODBCString = "##@BUILDER.strODBCString##";
##else##
$host="##@BUILDER.strConnectInfo1 s##";
$user="##@BUILDER.strConnectInfo2 s##";
$pwd="##@BUILDER.strConnectInfo3 s##";
$dbname="##@BUILDER.strConnectInfo4 s##";
$ODBCString = "##@BUILDER.strODBCString##";
##endif##

##elseif @BUILDER.connStringType=="oracle"##
$user="##@BUILDER.strConnectInfo1 s##";
$pwd="##@BUILDER.strConnectInfo2 s##";
$sid="##@BUILDER.strConnectInfo3 s##";

##elseif @BUILDER.connStringType=="postgre"##
$host="##@BUILDER.strConnectInfo1 s##";
$user="##@BUILDER.strConnectInfo2 s##";
$password="##@BUILDER.strConnectInfo3 s##";
$options="##@BUILDER.strConnectInfo4 s##";
$dbname="##@BUILDER.strConnectInfo5 s##";

$connstr=	"host='".pg_escape_string($host).
			"' user='".pg_escape_string($user).
			"' password='".pg_escape_string($password).
			"' dbname='".pg_escape_string($dbname).
			"' ".$options;

##elseif @BUILDER.connStringType=="db2"##
$host="##@BUILDER.strConnectInfo1 s##";
$port="##@BUILDER.strConnectInfo2 s##";
$user="##@BUILDER.strConnectInfo3 s##";
$pwd="##@BUILDER.strConnectInfo4 s##";
$dbname="##@BUILDER.strConnectInfo5 s##";

##elseif @BUILDER.connStringType=="informix"##
$host="##@BUILDER.strConnectInfo1 s##";
$user="##@BUILDER.strConnectInfo2 s##";
$pwd="##@BUILDER.strConnectInfo3 s##";
$dbname="##@BUILDER.strConnectInfo4 s##";

##elseif @BUILDER.connStringType=="sqlite"##
$dbname="##@BUILDER.strConnectInfo1 s##";

##elseif @BUILDER.connStringType=="msaccess" || @BUILDER.connStringType=="odbc" || @BUILDER.connStringType=="odbcdsn" || @BUILDER.connStringType=="custom" || @BUILDER.connStringType=="file"##
$ODBCString = "##@BUILDER.strODBCString##";
##endif##

