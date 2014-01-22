#!/bin/sh

echo MAKE SURE YOU HAVE A DIRECTORY $4

#set PGPASSWORD= $2
export PGPASSWORD=$2

cd $4

RES=`./psql -d lc -U $1 -f $3//add-light-data.sql`
