#!/usr/bin/env bash

#获取当前脚本路径
basedir=`cd $(dirname $0); pwd -P`
appsfile="$basedir/app/src/main/assets/apps"
echo "可用包名列表:"
for i in $appsfile/*
do
    echo "${i##*/}"
done

#=======================修改AndroidManifest.xml包名=========================
#临时文件
temp=temp.txt
#文件目录
rfile="$basedir/app/src/main/AndroidManifest.xml"
#指定内容写入temp.txt
grep -Eo "package=\"[^\s]+[a-z]+\.+[a-z]*" $rfile > $temp
#读取内容
tfile="$basedir/$temp"
olddata=$(cat $tfile)
echo "原来包名:$olddata"

#=========================控制台输入包名=========================
echo "请输入包名:"
read input
preinput=${input:0:4}
#检查com.开头
if [ "$preinput" != "" ]; then
    if [ "$preinput" != "com." ]; then
        echo "请重新输入包名:"
        read input
    fi
else
    #获取长度
    length=${#olddata}
    input=${olddata:9:length}
fi
#将某个文件中的jack字符串替换为tom
newdata="package=\"$input"
s="s/$olddata/$newdata/g"
# s="s/oldsland/travel/g"

#这个地方有个坑 要加一个 ""
sed -i "" "$s" $rfile
# sed -i "s/oldsland/travel/g" `grep "oldsland" -rl ./`

#成功后删除
rm -f $tfile

#=======================拷贝启动图片=========================
icondir1="$basedir/app/src/main/assets/apps/$input/www/assets/icon.png"
icondir2="$basedir/app/src/main/res/drawable-xxhdpi/icon.png"
cp -f $icondir1 $icondir2

splashdir1="$basedir/app/src/main/assets/apps/$input/www/assets/splash.png"
splashdir2="$basedir/app/src/main/res/drawable-xxhdpi/splash.png"
cp -f $splashdir1 $splashdir2

#=======================修改dcloud_control.xml包名=========================
temp2=temp2.txt
rfile2="$basedir/app/src/main/assets/data/dcloud_control.xml"
grep -Eo "appid=\"[^\s]+[a-z]+\.+[a-z]*" $rfile2 > $temp2
tfile2="$basedir/$temp2"
olddata2=$(cat $tfile2)
newdata2="appid=\"$input"
s2="s/$olddata2/$newdata2/g"
sed -i "" "$s2" $rfile2
rm -f $tfile2

#打包
echo "打包......"
gradle assembleRelease

