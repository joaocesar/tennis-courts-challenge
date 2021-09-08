package com.tenniscourts.guests;

import com.tenniscourts.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public Guest findById(Long guestId) {
        return guestRepository.findById(guestId).orElseThrow(EntityNotFoundException::new);
    }
}
