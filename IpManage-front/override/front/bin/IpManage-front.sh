#!/bin/bash

SH_HOME=$(cd "$(dirname "$0")"; pwd)
BASE_HOME=$SH_HOME/..
cd $SH_HOME
umask 077
# source ./util.sh

echo "SH_HOME = $SH_HOME"
echo "BASE_HOME = $BASE_HOME"
function startNginx()
{
    SH_HOME=$(cd "$(dirname "$0")"; pwd)
    BASE_HOME=$SH_HOME/..

    echo "startNginx SH_HOME = $SH_HOME"
    echo "startNginx BASE_HOME = $BASE_HOME"

    $BASE_HOME/nginx/sbin/nginx -p $BASE_HOME/ -c $BASE_HOME/conf/nginx.conf
    if [ ! $? -eq 0 ]; then
        echo "IpManage-front start fail"
        exit 1
    fi
    cp $BASE_HOME/logs/nginx.pid $BASE_HOME/logs/nginx.pid.back
    rm -rf $BASE_HOME/logs/nginx.pid
    mv $BASE_HOME/logs/nginx.pid.back $BASE_HOME/logs/nginx.pid
}

function stopNginx()
{
     $BASE_HOME/nginx/sbin/nginx -p $BASE_HOME/ -c $BASE_HOME/conf/nginx.conf -s stop
}

if [ ! -d "$BASE_HOME/logs" ]; then
	mkdir $BASE_HOME/logs
fi

if [ ! -d "$BASE_HOME/temp" ]; then
	mkdir $BASE_HOME/temp
fi

case $1 in
    start)
        startNginx
        ;;
    stop)
        stopNginx
        ;;
    uninstall)
    	stopNginx
    	cd $BASE_HOME
    	rm -rf $(pwd)
        ;;
    restart)
        stopNginx
        startNginx
        ;;
    test)
        ps aux | grep '$BASE_HOME/nginx/sbin/' | grep -v grep
        ;;
    status)
        check_service --service-name IpManage-front --shell="</dev/tcp/192.168.0.28/65522"
        if [ -f $BASE_HOME/keepalived/keepalived_nginx.conf ];then
            if [ -z "$(ps aux | grep keepalived_nginx | grep -v grep | wc -l)" ];then
                echo "keepalived_nginx service is not available!"
                exit 1
            else
                echo "keepalived_nginx service is available!"
            fi
        fi
        ;;
esac


sleep 1