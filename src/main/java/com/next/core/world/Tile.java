package com.next.core.world;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tile {
    private BufferedImage image;
    private boolean solid;
}
