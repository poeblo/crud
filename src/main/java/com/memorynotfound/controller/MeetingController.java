package com.memorynotfound.controller;

import com.memorynotfound.model.Meeting;
import com.memorynotfound.model.User;
import com.memorynotfound.service.MeetingService;
import com.memorynotfound.service.Mservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    private Mservice mservice =  new MeetingService();

    @RequestMapping
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting){
        Meeting rmeeting = mservice.createMeeting(meeting);
        if (rmeeting==null){
            return new ResponseEntity<Meeting>(HttpStatus.CONFLICT);
        }
        return  new ResponseEntity<Meeting>(rmeeting,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Meeting>> getAll(){
        List<Meeting> meeting= mservice.getAllMeeting();
        if (meeting == null || meeting.isEmpty()){
            return new ResponseEntity<List<Meeting>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Meeting>>(meeting,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Meeting>> getUsersMeetings(){
        List<Meeting> meeting= mservice.getUserMeetings(ProfileCotroller.uid);
        if (meeting == null || meeting.isEmpty()){
            return new ResponseEntity<List<Meeting>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Meeting>>(meeting,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Meeting> getMeetById(@PathVariable("id") int id){
        Meeting meeting = mservice.getMettById(ProfileCotroller.uid,id);
        if (meeting==null){
            return new ResponseEntity<Meeting>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Meeting>(meeting,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/{uid}",method = RequestMethod.POST)
    public ResponseEntity<Void>addUser(@PathVariable("id") int id,@PathVariable("uid")String uid){
        mservice.addUser(id,uid);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}/{uid}",method = RequestMethod.DELETE)
    public ResponseEntity<Void>deleteUser(@PathVariable("id") int id,@PathVariable("uid") String uid){
        mservice.deleteUser(id,uid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Meeting>update(@RequestBody Meeting meeting){
        Meeting meeting1 = mservice.update(meeting);
        if (meeting1==null){
            return new ResponseEntity<Meeting>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Meeting>(meeting,HttpStatus.OK);
    }
}
