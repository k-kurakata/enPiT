#!/bin/bash
db_name=$1
rec_id=$2
method=$3
mission_text=`sqlite3 ${db_name} "select body from missions where id = ${rec_id}"`
echo "${mission_text}"
if [ "add" = ${method} ];then
  echo "add"
  echo "${mission_text}" | nc -w0 127.0.0.1 10007
elif [ "delete" = ${method} ];then
  echo "delete"
  echo "${mission_text}" | nc -w0 127.0.0.1 10008
else
  echo ""
  exit;
fi
