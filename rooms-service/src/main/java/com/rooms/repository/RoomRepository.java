package com.rooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rooms.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
