#!/bin/sh

echo MAKE SURE YOU HAVE A DIRECTORY $4

#set PGPASSWORD= $2
export PGPASSWORD=$2

cd $4

RES=`./psql -d lc -U $1 -f $3//add-lc-test-data.sql`
RES=`./psql -d lc -U $1 -f $3//add-lc-model-number-data.sql`
RES=`./psql -d lc -U $1 -f $3//insert-property-value.sql`
RES=`./psql -d lc -U $1 -f $3//insert-operational-data.sql`
RES=`./psql -d lc -U $1 -f $3//add-lc-test-data-analytics.sql`
RES=`./psql -d lc -U $1 -f $3//insert-by-lat-lng.sql`
RES=`./psql -d lc -U $1 -f $3//update-smartpoint-rni-id.sql`
RES=`./psql -d lc -U $1 -f $3//portland-data.sql`
RES=`./psql -d lc -U $1 -f $3//insert-light-property-city-value.sql`
RES=`./psql -d lc -U $1 -f $3//insert-light-time-zone-value.sql`
RES=`./psql -d lc -U $1 -f $3//add-lc-test-data-add-schedule-membership.sql`
RES=`./psql -d lc -U $1 -f $3//update-center.sql`
RES=`./psql -d lc -U $1 -f $3//dashboard-resume-data.sql`
RES=`./psql -d lc -U $1 -f $3//drop-temp-functions.sql`
