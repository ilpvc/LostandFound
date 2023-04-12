package com.example.lostandfound.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @date:2023/4/12 16:12
 * @author: ilpvc
 */
@Data
@Component
public class Images {
    List<String> images = new ArrayList<>();
}
