#!/bin/bash
echo "web: " > Procfile
git init
git add -A
git commit -m "first commit"
heroku create 
heroku addons:create memcachier:dev
heroku addons:create cleardb:ignite
url=$(heroku config | grep CLEARDB_DATABASE_URL)
IFS=": // / ? @"
set $url
array=$@
db_user=$(echo $array | cut -d " " -f 3)
db_password=$(echo $array | cut -d " " -f 4)
db_host=$(echo $array | cut -d " " -f 5)
db_database=$(echo $array | cut -d " " -f 6)
heroku config:set DB_USER=$db_user DB_PASSWORD=$db_password DB_HOST=$db_host DB_DATABASE=$db_database
heroku addons:create librato:development
heroku addons:create mongolab:sandbox
heroku addons:create logdna:quaco
git push heroku master
heroku dyno:type free
heroku ps: scale web=1
heroku open
