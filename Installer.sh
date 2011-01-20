#!/bin/sh

REVISION="`svn info 2>> /dev/null|grep vision|grep -v modif|sed 's/^R\(.*\)\s//'`"
DESTINATIONFOLDER="Kanban-r$REVISION"

rm -rf "$DESTINATIONFOLDER"
mkdir "$DESTINATIONFOLDER"

cp -r scripts/* dist/* "$DESTINATIONFOLDER/"

