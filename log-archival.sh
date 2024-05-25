#!/bin/bash
: '
  @author aryak

  This is a script that manages application & tomcat access log archival for a spring boot application.
  Run this script as a cron job every night 12 am to take log archival of previous day.
'
APP_BASE="/Users/aryak/Desktop/book-app"
LOGS_DIR="${APP_BASE}/logs"
TOMCAT_ACCESS_LOG_DIR="${APP_BASE}/tomcat/access_logs/"
DESTINATION_DIR="/home/ec2-user/backups"
BACKUP_SERVER="ec2-user@3.26.38.137"
SSH_KEY="/Users/aryak/Downloads/my-keypair.pem"
#LOG_FILE="/Users/aryak/Desktop/book-app/logs/transfer.log"
PREVIOUS_DAY=$(date -v -1d +"%Y-%m-%d")
# PREVIOUS_DAY=$(date -d "yesterday" +"%Y-%m-%d")   -> gives error on Mac OS

ssh -i "$SSH_KEY" "$BACKUP_SERVER" "mkdir -p $DESTINATION_DIR/${PREVIOUS_DAY} $DESTINATION_DIR/${PREVIOUS_DAY}/tomcat"
scp -i "$SSH_KEY" "$LOGS_DIR"/*.log.gz "$BACKUP_SERVER:$DESTINATION_DIR/${PREVIOUS_DAY}/"
scp -i "$SSH_KEY" "TOMCAT_ACCESS_LOG_DIR"/*.log "$BACKUP_SERVER:$DESTINATION_DIR/${PREVIOUS_DAY}/"
count=$(ls $LOGS_DIR/*.log.gz |wc -l)
#echo "$(date +'%Y:%m:%d:%H:%M:%S') | Transferred $count log files to $DESTINATION_DIR/$PREVIOUS_DAY" >> "$LOG_FILE"