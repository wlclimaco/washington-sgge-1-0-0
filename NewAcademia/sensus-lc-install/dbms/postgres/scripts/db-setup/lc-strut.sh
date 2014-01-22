#!/bin/sh

echo MAKE SURE YOU HAVE A DIRECTORY $4

#set PGPASSWORD= $2
export PGPASSWORD=$2

cd $4
#cd /PostgreSQL/9.0/bin

./dropdb --username=$1 lc
./createdb --username=$1 --owner=$1 --encoding=UTF8 --tablespace=pg_default lc
RES=`./psql -d lc -U $1 -f $3//table-func.sql`
RES=`./psql -d lc -U $1 -f $3//create-tables.sql`
RES=`./psql -d lc -U $1 -f $3//create-views.sql`
RES=`./psql -d lc -U $1 -f $3//create-functions.sql`
RES=`./psql -d lc -U $1 -f $3//add-triggers.sql`
RES=`./psql -d lc -U $1 -f $3//add-lc-code-table-data.sql`
RES=`./psql -d lc -U $1 -f $3//dashboard-resume-data.sql`

#reset PGPASSWORD
PGPASSWORD=""
export PGPASSWORD
#End
