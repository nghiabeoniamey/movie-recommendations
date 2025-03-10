# 电影推荐系统

## 目录

- [介绍](#介绍)
- [角色与职责](#角色与职责)
- [系统要求](#系统要求)
- [安装与使用](#安装与使用)
- [联系方式](#联系方式)

## 介绍

**电影推荐系统** 是基于用户偏好和观看历史的电影推荐系统。该系统采用混合推荐模型来分析数据，提供准确且相关的电影推荐。

该系统帮助管理员管理电影数据、用户信息及相关数据。用户可以搜索电影、评分、查看收藏列表，并根据兴趣获取推荐。

## 角色与职责

### 管理员:
- 管理电影列表：添加、编辑和删除电影。
- 监控用户的电影观看历史。
- 审核用户的电影评论。
- 管理用户账户信息。

### 用户:
- 按类型、标题或演员搜索电影。
- 评分和评论电影。
- 查看收藏电影列表。
- 根据观看历史和个人偏好获取推荐。

## 系统要求

### 后端

- JDK >= 21（推荐）
- Python 3.12.9（推荐）
- IntelliJ IDEA >= 2020.3（推荐 MAX-VERSION）
- MySQL（推荐 MAX-VERSION）
- Python >= 3.10（推荐）
- Flask/FastAPI（用于推荐系统）
- Python 库：Pandas, NumPy, Scikit-learn, TensorFlow/PyTorch（如果使用机器学习）

### 前端

- Node.js >= 14.0.0（推荐 v20.15.0）
- npm >= 6.0.0（推荐 v10.7.0）
- Visual Studio Code

## 安装与使用

### 后端

克隆存储库：

```sh
git clone https://github.com/nghiabeoniamey/movie-recommendations
```

#### 服务器: Java + Spring Boot

1. 在 IntelliJ IDEA 中打开后端项目，并等待加载 Gradle 项目。
2. 配置环境变量：

```.env
# 数据库信息
MYSQL_HOST=localhost
MYSQL_PORT=3306
MYSQL_DATABASE=movie_recommendations
MYSQL_USER=root
MYSQL_PASSWORD=123456 [** / (需要更改) \ **]
JPA_SHOW_SQL=true
JPA_DDL_AUTO=none
GENERATE_DB=true

# 服务器信息
SERVER_VERSION=1.0.0
SERVER_PORT=6868

# 前端信息
FRONTEND_URL=http://localhost:8888/
BACKEND_URL=http://localhost:6868
PYTHON_URL=http://localhost:8000/
TIME_ZONE=Asia/Ho_Chi_Minh

# 邮件
SPRING_MAIL_USERNAME=nghiabe.dev@gmail.com
SPRING_MAIL_PASSWORD=

# 认证
JWT_SECRET=rKFEVZaH+KAUSbnsxiRfFe8VEnMez8Bi7lF/aCXCsoVtRSaCs4cK9XJoiR1WqpcbhKbNIvB15n6lHv3HMnKLp7R0QQ0a8/DVnqGcm84XKE5j9P1MSk4vY1AspKuHnnb6c9gUtv8lHkJ8uinTas/cyQrgcrNQXCKQP10PVJw4OAx6
```

3. 运行后端项目。

#### 推荐系统: Python

1. 设置 Python 虚拟环境：

```sh
python -m venv venv
source venv/bin/activate  # macOS/Linux
venv\Scripts\activate  # Windows
```

2. 安装依赖项：

```sh
pip install -r requirements.txt
```

3. 运行推荐系统：

```sh
python app.py
```

### 前端

克隆存储库：

```sh
git clone https://github.com/hieuhoang26/Movie_FE.git
```

1. 在 Visual Studio Code 中打开前端项目。
2. 配置环境变量：

```.env
VITE_API_KEY=eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyODg3NmMzZGQyMTE5YjMyM2MwMGNmYzUyNDRhOWIyOCIsIm5iZiI6MTc0MDk3ODI2MC44OTc5OTk4LCJzdWIiOiI2N2M1Mzg1NDQ4ZWU5MDE1YWI3YTcxOTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.25ub4vnbboEIPwvlbEUIdXi9Uc3y1QfC71uFSju1iM8
VITE_IMG_URL=https://image.tmdb.org/t/p/w500/
```

3. 安装依赖项：

```sh
npm install
```

4. 启动前端：

```sh
npm start
```

## 联系方式

如有任何问题，请通过邮件或 Telegram 联系我们。

**Telegram:** @Nghiabe0512  
**Email:** nghiabe.dev@gmail.com  
**电话:** 0849070512