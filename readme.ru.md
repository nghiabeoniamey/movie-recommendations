# Рекомендации по фильмам

## Содержание

- [Введение](#введение)
- [Акторы и их роли](#акторы-и-их-роли)
- [Системные требования](#системные-требования)
- [Установка и использование](#установка-и-использование)
- [Контакты](#контакты)

## Введение

**Рекомендации по фильмам** — это система рекомендаций фильмов, основанная на предпочтениях пользователей и их истории просмотров. Система использует гибридную модель рекомендаций для анализа данных и предоставления точных и релевантных предложений фильмов.

Эта система помогает администраторам управлять базой данных фильмов, пользователями и связанной информацией. Пользователи могут искать фильмы, оценивать их, просматривать список избранного и получать персонализированные рекомендации.

## Акторы и их роли

### Администратор:
- Управление списком фильмов: добавление, редактирование и удаление фильмов.
- Мониторинг истории просмотров пользователей.
- Модерация отзывов пользователей о фильмах.
- Управление учетными записями пользователей.

### Пользователь:
- Поиск фильмов по жанру, названию или актеру.
- Оценка и рецензирование фильмов.
- Просмотр списка избранных фильмов.
- Получение рекомендаций на основе истории просмотров и предпочтений.

## Системные требования

### Backend

- JDK >= 21 (рекомендуется)
- Python 3.12.9 (рекомендуется)
- IntelliJ IDEA >= 2020.3 (рекомендуется MAX-VERSION)
- MySQL (рекомендуется MAX-VERSION)
- Python >= 3.10 (рекомендуется)
- Flask/FastAPI (для системы рекомендаций)
- Библиотеки Python: Pandas, NumPy, Scikit-learn, TensorFlow/PyTorch (если используется машинное обучение)

### Frontend

- Node.js >= 14.0.0 (рекомендуется v20.15.0)
- npm >= 6.0.0 (рекомендуется v10.7.0)
- Visual Studio Code

## Установка и использование

### Backend

Клонирование репозитория:

```sh
 git clone https://github.com/nghiabeoniamey/movie-recommendations
```

#### Сервер: Java + Spring Boot

1. Откройте проект backend в IntelliJ IDEA и дождитесь загрузки проекта Gradle.
2. Настройте переменные окружения:

```.env
# Информация о базе данных
MYSQL_HOST=localhost
MYSQL_PORT=3306
MYSQL_DATABASE=movie_recommendations
MYSQL_USER=root
MYSQL_PASSWORD=123456 [** / (необходимо изменить) \ **]
JPA_SHOW_SQL=true
JPA_DDL_AUTO=create
GENERATE_DB=true

# Информация о сервере
SERVER_VERSION=1.0.0
SERVER_PORT=6868

# Информация о frontend
FRONTEND_URL=http://localhost:8888/
BACKEND_URL=http://localhost:6868
PYTHON_URL=http://localhost:8000/
TIME_ZONE=Asia/Ho_Chi_Minh

# Email
SPRING_MAIL_USERNAME=nghiabe.dev@gmail.com
SPRING_MAIL_PASSWORD=

# Аутентификация
JWT_SECRET=rKFEVZaH+KAUSbnsxiRfFe8VEnMez8Bi7lF/aCXCsoVtRSaCs4cK9XJoiR1WqpcbhKbNIvB15n6lHv3HMnKLp7R0QQ0a8/DVnqGcm84XKE5j9P1MSk4vY1AspKuHnnb6c9gUtv8lHkJ8uinTas/cyQrgcrNQXCKQP10PVJw4OAx6
```

3. Запустите backend.

#### Система рекомендаций: Python

1. Настроить виртуальное окружение:

```sh
python -m venv venv
source venv/bin/activate  # macOS/Linux
venv\Scripts\activate  # Windows
```

2. Установить зависимости:

```sh
pip install -r requirements.txt
```

3. Запустить систему рекомендаций:

```sh
python main.py
```

### Frontend

Клонирование репозитория:

```sh
 git clone https://github.com/hieuhoang26/Movie_FE.git
```

1. Откройте frontend-проект в Visual Studio Code.
2. Настройте переменные окружения:

```.env
VITE_API_KEY=eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyODg3NmMzZGQyMTE5YjMyM2MwMGNmYzUyNDRhOWIyOCIsIm5iZiI6MTc0MDk3ODI2MC44OTc5OTk4LCJzdWIiOiI2N2M1Mzg1NDQ4ZWU5MDE1YWI3YTcxOTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.25ub4vnbboEIPwvlbEUIdXi9Uc3y1QfC71uFSju1iM8
VITE_IMG_URL=https://image.tmdb.org/t/p/w500/
```

3. Установить зависимости:

```sh
npm install
```

4. Запустить frontend:

```sh
npm run dev
```

## Контакты

Если у вас есть вопросы, свяжитесь с нами по email или в Telegram.

**Telegram:** @Nghiabe0512  
**Email:** nghiabe.dev@gmail.com  
**Телефон:** 0849070512