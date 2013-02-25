#!/bin/sh

echo MAKE SURE YOU HAVE A DIRECTORY $4

#set PGPASSWORD= $2
export PGPASSWORD=$2

cd $4
#cd /PostgreSQL/9.0/bin

RES=`./psql -d lc -U $1 -f $3//update-functions.sql`
RES=`./psql -d lc -U $1 -f $3//update-data.sql`
RES=`./psql -d lc -U $1 -f $3//update-views.sql`
RES=`./psql -d lc -U $1 -f $3//update-tables.sql`
RES=`./psql -d lc -U $1 -f $3//update-triggers.sql`
RES=`./psql -d lc -U $1 -f $3//dashboard-resume-data.sql`

echo Finished

#reset PGPASSWORD
PGPASSWORD=""
export PGPASSWORD
#End
