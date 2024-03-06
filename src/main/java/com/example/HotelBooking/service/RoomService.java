package com.example.HotelBooking.service;


import com.example.HotelBooking.exception.PhotoNotFoundException;
import com.example.HotelBooking.exception.ResourceNotFoundException;
import com.example.HotelBooking.model.Room;
import com.example.HotelBooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws SQLException, IOException {
        Room room=new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if(!file.isEmpty()){
            byte[] photoByte= file.getBytes();
            Blob photoBlob=new SerialBlob(photoByte);
            room.setPhoto(photoBlob);
        }
        return roomRepository.save(room);
    }

    @Override
    public List<String> getAllRoomTypes() {

        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public List<Room> getAllRooms() {

        return roomRepository.findAll();
    }

    @Override
    public byte[] getRoomPhotoByRoomId(Long roomid) throws SQLException {
        Optional<Room> theRoom=roomRepository.findById((roomid));
        if(theRoom.isEmpty()){
            throw new ResourceNotFoundException("Sorry,Room not Found");
        }
        Blob photoBlob=theRoom.get().getPhoto();
        if(photoBlob!= null){
            return photoBlob.getBytes(1,(int) photoBlob.length());
        }
        return null;
    }

    @Override
    public void deleteRoom(Long roomId) {
        Optional<Room> theRoom=roomRepository.findById(roomId);
        if(theRoom.isPresent()){
            roomRepository.deleteById(roomId);
        }
    }

    @Override
    public Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) {
        Room room=roomRepository.findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room Not Found"));
        if(roomType!=null)room.setRoomType(roomType);
        if(roomPrice!=null)room.setRoomPrice(roomPrice);
        if(photoBytes!=null&&photoBytes.length>0){
            try{
                room.setPhoto(new SerialBlob(photoBytes));
            }catch(SQLException e){
                throw new PhotoNotFoundException("Error updating photo");

            }
        }
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getRoomById(Long roomId) {
        return Optional.of(roomRepository.findById(roomId).get());
    }

    @Override
    public List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        return roomRepository.findAvailableRoomByDatesAndType(checkInDate,checkOutDate,roomType);
    }
}
