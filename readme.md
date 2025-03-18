# Gợi ý Phim  

## Mục lục  

- [Giới thiệu](#giới-thiệu)  
- [Các vai trò](#các-vai-trò)  
- [Yêu cầu hệ thống](#yêu-cầu-hệ-thống)  
- [Cài đặt và sử dụng](#cài-đặt-và-sử-dụng)  
- [Liên hệ](#liên-hệ)  

## Giới thiệu  

**Gợi ý Phim** là một hệ thống đề xuất phim dựa trên sở thích và lịch sử xem của người dùng. Hệ thống sử dụng mô hình Gợi ý Kết hợp (Hybrid Recommendation System) để phân tích dữ liệu và đưa ra các đề xuất phim chính xác và phù hợp.  

Hệ thống hỗ trợ quản trị viên trong việc quản lý dữ liệu phim, người dùng và các thông tin liên quan. Người dùng có thể tìm kiếm, đánh giá, xem danh sách phim yêu thích và nhận gợi ý phim dựa trên sở thích cá nhân.  

### Các vai trò  

#### Quản trị viên:  
- Quản lý danh sách phim: thêm, sửa, xóa.  
- Theo dõi lịch sử xem phim của người dùng.  
- Kiểm duyệt đánh giá phim của người dùng.  
- Quản lý thông tin tài khoản người dùng.  

#### Người dùng:  
- Tìm kiếm phim theo thể loại, tiêu đề hoặc diễn viên.  
- Đánh giá và nhận xét phim.  
- Xem danh sách phim yêu thích.  
- Nhận đề xuất phim dựa trên lịch sử xem và sở thích cá nhân.  

## Yêu cầu hệ thống  

### Dự án Backend  

- JDK >= 21 (khuyến nghị)  
- Python >= 3.10 (khuyến nghị 3.12.9) 
- IntelliJ IDEA >= 2020.3 (khuyến nghị MAX-VERSION)  
- MySQL (khuyến nghị MAX-VERSION)  
- Flask/FastAPI (cho Hệ thống Gợi ý)  
- Các thư viện Python: Pandas, NumPy, Scikit-learn, TensorFlow/PyTorch

### Dự án Frontend  

- Node.js >= 14.0.0 (khuyến nghị v20.15.0)  
- npm >= 6.0.0 (khuyến nghị v10.7.0)  
- Visual Studio Code  

## Cài đặt và sử dụng  

Clone repository:  

```sh  
git clone https://github.com/nghiabeoniamey/movie-recommendations  
```  

### Dự án Backend  

#### Server: Java + Spring Boot  

1. Mở backend bằng IntelliJ IDEA và chờ IDE tải dự án Gradle.  
2. Cấu hình biến môi trường:  

```.env  
#THÔNG TIN DATABASE  
MYSQL_HOST=localhost  
MYSQL_PORT=3306  
MYSQL_DATABASE=movie_recommendations  
MYSQL_USER=root  
MYSQL_PASSWORD=123456 [** / (cần thay đổi) \ **]  
JPA_SHOW_SQL=true  
JPA_DDL_AUTO=create  
GENERATE_DB=true  

#THÔNG TIN SERVER  
SERVER_VERSION=1.0.0  
SERVER_PORT=6868  

#THÔNG TIN FRONTEND  
FRONTEND_URL=http://localhost:8888/  
BACKEND_URL=http://localhost:6868  
PYTHON_URL=http://localhost:8000/  
TIME_ZONE=Asia/Ho_Chi_Minh  

#Email  
SPRING_MAIL_USERNAME=nghiabe.dev@gmail.com  
SPRING_MAIL_PASSWORD=  

#THÔNG TIN XÁC THỰC  
JWT_SECRET=rKFEVZaH+KAUSbnsxiRfFe8VEnMez8Bi7lF/aCXCsoVtRSaCs4cK9XJoiR1WqpcbhKbNIvB15n6lHv3HMnKLp7R0QQ0a8/DVnqGcm84XKE5j9P1MSk4vY1AspKuHnnb6c9gUtv8lHkJ8uinTas/cyQrgcrNQXCKQP10PVJw4OAx6  
```  

3. Chạy dự án Backend.  

#### Hệ thống Gợi ý: Python  

1. Thiết lập môi trường Python:  

```sh  
python -m venv venv  
source venv/bin/activate  # Trên macOS/Linux  
venv\Scripts\activate  # Trên Windows  
```  

2. Cài đặt thư viện:  

```sh  
pip install -r requirements.txt  
```  

3. Chạy Hệ thống Gợi ý:  

```sh  
python main.py  
```  

### Dự án Frontend: Client  

1. Mở frontend bằng Visual Studio Code.  
2. Cấu hình biến môi trường:  

```.env  
VITE_API_KEY=eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyODg3NmMzZGQyMTE5YjMyM2MwMGNmYzUyNDRhOWIyOCIsIm5iZiI6MTc0MDk3ODI2MC44OTc5OTk4LCJzdWIiOiI2N2M1Mzg1NDQ4ZWU5MDE1YWI3YTcxOTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.25ub4vnbboEIPwvlbEUIdXi9Uc3y1QfC71uFSju1iM8  
VITE_IMG_URL=https://image.tmdb.org/t/p/w500/  
```  

3. Cài đặt thư viện:  

```sh  
npm install  
```  

4. Chạy frontend:  

```sh  
npm run dev  
```  

## Liên hệ  

Nếu bạn có bất kỳ câu hỏi nào, vui lòng liên hệ qua email hoặc Telegram:  

Telegram: @Nghiabe0512  

Gmail: nghiabe.dev@gmail.com  

Số điện thoại: 0849070512  