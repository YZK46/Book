# Book
图书推荐平台

## 一、git命令

* git status #查看文件状态（红为改动了的，绿的为已提交）
* git add [file1] [file2]  #添加指定文件到暂存区
* git add .                     #添加当前目录所有文件到暂存区
* git reset [file1]  #将暂存区的文件回退到工作区
* git commit -m [message] #提交暂存区到仓库区
* git commit -a 提交工作区自上次commit之后的变化，直接到仓库区
* git fetch [remote]  #下载远程仓库的所有变动
* git push  #提交本地仓库到远程仓库
* git checkout [file] # 恢复暂存区的指定文件到工作区
* git log  #查看提交日志
* 