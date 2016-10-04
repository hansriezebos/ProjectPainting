package com.projectpainting.dao;

import java.util.List;

import com.projectpainting.model.*;

public interface PaintingDAO {

	public void addPainting(Painting painting);
	public void deletePainting( int id );
	public void updatePainting( Painting Painting );
	public List<Painting> getAllPaintings(int userId);
	public Painting getPaintingById( int id );
	public List<Integer> getAllStatistics();
}