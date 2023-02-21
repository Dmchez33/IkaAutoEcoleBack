package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.VideoForm;
import com.ikaautoecole.spring.projet.repository.VideoFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoFormServiceImpl {

    @Autowired
    VideoFormRepository videoFormRepository;

    public VideoForm addVideoForm(VideoForm videoForm)
    {
        return videoFormRepository.save(videoForm);
    }

    public List<VideoForm> getAllVideoForm(){
        return videoFormRepository.findAll();
    }

    public VideoForm getVideoFormById(Long id){
        return videoFormRepository.getReferenceById(id);
    }


}
