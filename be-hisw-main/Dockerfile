# Giai đoạn 1: Build ứng dụng
# Sử dụng một base image có chứa JDK để build dự án
FROM openjdk:21-jdk-slim AS build

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép mã nguồn vào container
# Sao chép pom.xml (hoặc build.gradle) trước để tận dụng Docker layer caching
COPY pom.xml .
COPY . .

# Build ứng dụng để tạo file JAR
# Bỏ qua các bài test để build nhanh hơn
RUN ./mvnw clean package -DskipTests


# Giai đoạn 2: Chạy ứng dụng
# Sử dụng một base image chỉ chứa JRE, nhẹ hơn nhiều
FROM eclipse-temurin:21-jre-jammy

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép file JAR đã được build từ giai đoạn 'build'
COPY --from=build /app/target/*.jar app.jar

# Expose port mà ứng dụng Spring Boot đang chạy (mặc định là 8080)
EXPOSE 8286

# Lệnh để khởi chạy ứng dụng khi container bắt đầu
ENTRYPOINT ["java", "-jar", "app.jar"]