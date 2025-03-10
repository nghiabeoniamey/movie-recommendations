# Movie Recommendations

## Table of Contents

- [Introduction](#introduction)
- [Actors](#actors-and-their-roles)
- [System Requirements](#system-requirements)
- [Installation and Usage](#installation-and-usage)
- [Contact](#contact)

## Introduction

**Movie Recommendations** is a movie recommendation system based on user preferences and viewing history. The system uses a Hybrid Recommendation System model to analyze data and provide accurate and relevant movie suggestions.

The system assists administrators in managing movie data, users, and related information. Users can search, rate, view favorite movie lists, and receive recommendations based on their interests.

### Actors and Their Roles

#### Admin:
- Manage the movie list: add, edit, delete.
- Monitor and track users' movie viewing history.
- Moderate user movie reviews.
- Manage user account information.

#### User:
- Search for movies by genre, title, or actor.
- Rate and review movies.
- View favorite movie lists.
- Receive movie recommendations based on viewing history and personal preferences.

## System Requirements

### Backend Project

- JDK >= 21 (recommended)
- Python 3.12.9 (recommended)
- IntelliJ IDEA >= 2020.3 (MAX-VERSION recommended)
- MySQL (MAX-VERSION recommended)
- Python >= 3.10 (recommended)
- Flask/FastAPI (for the Recommendation System)
- Python libraries: Pandas, NumPy, Scikit-learn, TensorFlow/PyTorch (if using Machine Learning)

### Frontend Project

- Node.js >= 14.0.0 (v20.15.0 recommended)
- npm >= 6.0.0 (v10.7.0 recommended)
- Visual Studio Code

## Installation and Usage

Clone the repository:

```sh
 git clone https://github.com/nghiabeoniamey/movie-recommendations
```

### Backend Project

#### Server: Java + Springboot

1. Open the backend with IntelliJ IDEA and wait for the IDE to load the Gradle project.
2. Configure environment variables:

```.env
#DATABASE INFORMATION
MYSQL_HOST=localhost
MYSQL_PORT=3306
MYSQL_DATABASE=movie_recommendations
MYSQL_USER=root
MYSQL_PASSWORD=123456 [** / (need change) \ **]
JPA_SHOW_SQL=true
JPA_DDL_AUTO=create
GENERATE_DB=true

#SERVER INFORMATION
SERVER_VERSION=1.0.0
SERVER_PORT=6868

#FRONTEND INFORMATION
FRONTEND_URL=http://localhost:8888/
BACKEND_URL=http://localhost:6868
PYTHON_URL=http://localhost:8000/
TIME_ZONE=Asia/Ho_Chi_Minh

#Email
SPRING_MAIL_USERNAME=nghiabe.dev@gmail.com
SPRING_MAIL_PASSWORD=

#AUTHENTICATION INFORMATION
JWT_SECRET=rKFEVZaH+KAUSbnsxiRfFe8VEnMez8Bi7lF/aCXCsoVtRSaCs4cK9XJoiR1WqpcbhKbNIvB15n6lHv3HMnKLp7R0QQ0a8/DVnqGcm84XKE5j9P1MSk4vY1AspKuHnnb6c9gUtv8lHkJ8uinTas/cyQrgcrNQXCKQP10PVJw4OAx6
```

3. Run the Backend Project.

#### Recommendation System: Python

1. Set up a Python environment:

```sh
python -m venv venv
source venv/bin/activate  # On macOS/Linux
venv\Scripts\activate  # On Windows
```

2. Install dependencies:

```sh
pip install -r requirements.txt
```

3. Run the Recommendation System:

```sh
python main.py
```

### Frontend Project: Client

1. Open the frontend with Visual Studio Code.
2. Configure environment variables:

```.env
VITE_API_KEY=eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyODg3NmMzZGQyMTE5YjMyM2MwMGNmYzUyNDRhOWIyOCIsIm5iZiI6MTc0MDk3ODI2MC44OTc5OTk4LCJzdWIiOiI2N2M1Mzg1NDQ4ZWU5MDE1YWI3YTcxOTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.25ub4vnbboEIPwvlbEUIdXi9Uc3y1QfC71uFSju1iM8
VITE_IMG_URL=https://image.tmdb.org/t/p/w500/
```

3. Install dependencies:

```sh
npm install
```

4. Start the frontend:

```sh
npm run dev
```

## Contact

If you have any questions, please contact us via email or tele

Tele: @Nghiabe0512

Gmail: nghiabe.dev@gmail.com

Phone: 0849070512 