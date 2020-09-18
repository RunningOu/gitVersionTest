#!/bin/bash

CURRENT_DIR=$(cd "$(dirname "$0")"; pwd)
cd $CURRENT_DIR

usage() {
    echo "usage:"
    echo "    sh install.sh optstring parameters"
    echo "    sh install.sh [options] [--] optstring parameters"
    echo "    sh install.sh [options] -o|--options optstring [options] [--] parameters"
    echo ""
    echo "Options:"
    echo "    --security-level                   security level(medium | high)"
    echo "    --disconf-host                     disconf ip list, e.g: 10.1.241.2"
    echo "    --disconf-port                     disconf port, default: 8081"
    echo "    --disconf-user                     disconf user, default: admin"
    echo "    --disconf-passwd                   disconf passwd, default: admin"
    echo "    --local-ip                         local node ip, e.g: 10.1.241.3"
    echo "    --remote-ips                       remote node ip list, e.g: 10.1.241.4,10.1.241.5"
    echo "    --install-role                     install role: single, master, slave, default: single"
    echo "    -h, --help                         help"
}

ARGS=`getopt -o h:: --long security-level:,disconf-host:,disconf-port:,disconf-user:,disconf-passwd:,local-ip:,remote-ips:,install-role:,running-user:,help:: -n 'install.sh' -- "$@"`

if [ $? != 0 ]; then
    usage
    exit 1
fi

# note the quotes around `$ARGS': they are essential!
#set 会重新排列参数的顺序，也就是改变$1,$2...$n的值，这些值在getopt中重新排列过了
eval set -- "$ARGS"

#经过getopt的处理，下面处理具体选项。
while true ; do
    case "$1" in
        --security-level)
            SECURITY_LEVEL=$2
            shift 2
            ;;
        --disconf-host)
            DISCONF_HOST=$2
            shift 2
            ;;
        --disconf-port)
            DISCONF_PORT=$2
            shift 2
            ;;
        --disconf-user)
            DISCONF_USER=$2
            shift 2
            ;;
        --disconf-passwd)
            DISCONF_PASS=$2
            shift 2
            ;;
        --local-ip)
            LOCAL_IP=$2
            shift 2
            ;;
        --remote-ips)
            REMOTE_IPS=$2
            shift 2
            ;;
        --install-role)
            INSTALL_ROLE=$2
            shift 2
            ;;
        --running-user)
            RUNNING_USER=$2
            shift 2
            ;;
        -h|--help)
            usage
            exit 1
            ;;
        --)
            break
            ;;
        *)
            echo "internal error!" ;
            exit 1
            ;;
    esac
done

source ./util.sh

echo "Prepare to install nginx....."
if [ -n "$(ps aux | grep IpManage-front | grep -v grep)" ]; then
    pkill -f /opt/uyun/IpManage/front/nginx/sbin
fi

INSTALL_DIR=/opt/uyun/IpManage
mkdir -p ${INSTALL_DIR}
\cp -r front/ ${INSTALL_DIR}
chown -R $RUNNING_USER:$RUNNING_USER $INSTALL_DIR
chmod +x ${INSTALL_DIR}/front/nginx/sbin/nginx
chmod +x ${INSTALL_DIR}/front/bin/IpManage-front.sh

# chgrp $RUNNING_USER ${INSTALL_DIR}/nginx
# chown -R $RUNNING_USER:$RUNNING_USER ${INSTALL_DIR}/nginx
chown -R $RUNNING_USER:$RUNNING_USER ${INSTALL_DIR}/front/
chmod -R -077 ${INSTALL_DIR}/front/ ${INSTALL_DIR}/front/

if [ -n "$RUNNING_USER" ]; then
sed -i 's%#user  nobody;%user  ${RUNNING_USER};%' ${INSTALL_DIR}/front/conf/nginx.conf
else
sed -i 's%#user  nobody;%user  root;%' ${INSTALL_DIR}/front/conf/nginx.conf
fi
su $RUNNING_USER -c "sh /opt/uyun/IpManage/front/bin/IpManage-front.sh start"
check_stat IpManage-front 65522



