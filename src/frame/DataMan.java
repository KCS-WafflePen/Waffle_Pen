package Frame;

import java.sql.*;

public class DataMan {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/KAKAO";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "0928";
    private static final String TABLE_NAME = "KCS";

    private static final String INSERT_QUERY = "INSERT INTO " + TABLE_NAME + "(codeID, ip, port) VALUES (?, ?, ?);";

    private Connection connection;

    public DataMan() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveInviteCodeToDatabase(String codeID, String ip, float port) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, codeID);
            preparedStatement.setString(2, ip);
            preparedStatement.setFloat(3, port);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isInviteCodeValid(String codeID) {
        try {
            // 데이터베이스 연결
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL 쿼리 작성
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE codeID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, codeID);

                // 쿼리 실행 및 결과 확인
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // 결과가 존재하면 유효한 코드
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 에러가 발생하면 유효하지 않은 코드로 처리
        }
    }

}
