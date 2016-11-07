#!/bin/bash
echo "web: " > Procfile
git init
git add -A
git commit -m "first commit"
heroku create 
git push heroku master
heroku dyno:type free
heroku ps: scale web=1
heroku open
