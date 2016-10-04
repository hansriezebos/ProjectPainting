package com.projectpainting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projectpainting.model.Painting;
import com.projectpainting.util.DBUtil;

public class PaintingDAOImplementation implements PaintingDAO {
	
	private Connection conn;
	private Statement stmt;
	private Statement stmt2;

	public PaintingDAOImplementation() {
		conn = DBUtil.getConnection();
	}
	
	@Override
	public void addPainting(Painting painting) {
		try {
			String sql = "INSERT INTO paintings (users_id, name, image_data) VALUES (?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt( 1, painting.getUserId() );
			preparedStatement.setString( 2, painting.getName() );
			preparedStatement.setString( 3, painting.getImageData() );
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deletePainting( int paintingId ) {
		try {
			String sql = "DELETE FROM paintings WHERE id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, paintingId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePainting( Painting painting ) {
		try {
			String sql = "UPDATE paintings SET name=?, image_data=? WHERE id=?";
			PreparedStatement preparedStatement = conn.prepareStatement( sql );
			preparedStatement.setString( 1, painting.getName() );
			preparedStatement.setString( 2, painting.getImageData() );
			preparedStatement.setInt( 3, painting.getId() );
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Painting> getAllPaintings(int userId) {
		List<Painting> paintings = new ArrayList<Painting>();
		try {
			String sql = "SELECT * FROM paintings WHERE users_id = '" + userId + "' ORDER BY id DESC";
			stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			while( resultSet.next() ) {
				Painting painting = new Painting();
				painting.setId( resultSet.getInt( "id" ) );
				painting.setUserId( resultSet.getInt( "users_Id" ) );
				painting.setName( resultSet.getString( "name" ) );
				painting.setImageData( resultSet.getString( "image_data" ) );
				paintings.add(painting);
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return paintings;
	}
	
	@Override
	public Painting getPaintingById(int paintingId) {
		Painting painting = new Painting();
		try {
			String sql = "SELECT * FROM paintings where id = '" + paintingId + "'";
			stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			while( resultSet.next() ) {
				painting.setId( resultSet.getInt( "id" ) );
				painting.setUserId( resultSet.getInt( "users_Id" ) );
				painting.setName( resultSet.getString( "name" ) );
				painting.setImageData( resultSet.getString( "image_data" ) );
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return painting;
	}

	@Override
	public List<Integer> getAllStatistics() {
		List<Integer> statistics = new ArrayList<Integer>();
		try {
			String sqlPaintings = "SELECT * FROM paintings";
			String sqlUsers = "SELECT * FROM users";
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			ResultSet resultSetPaintings = stmt.executeQuery(sqlPaintings);
			ResultSet resultSetUsers = stmt2.executeQuery(sqlUsers);
			int totalPaintings = 0;
			while ( resultSetPaintings.next() ) {
				totalPaintings++;
			}
			int totalUsers = 0;
			while ( resultSetUsers.next() ) {
				totalUsers++;
			}
			statistics.add(totalPaintings);
			statistics.add(totalUsers);
			resultSetPaintings.close();
			resultSetUsers.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statistics;
	}
}