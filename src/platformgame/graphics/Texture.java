package platformgame.graphics;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs, ps;

    private BufferedImage blockSheet;
    private BufferedImage playerSheet;
    private BufferedImage level;
    private BufferedImage level2;

    public BufferedImage[] block = new BufferedImage[2];
    public BufferedImage[] player = new BufferedImage[18];

    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            blockSheet = loader.loadImage("/platformgame/resources/object/block.png");
            playerSheet = loader.loadImage("/platformgame/resources/object/player.png");
            level = loader.loadImage("/platformgame/resources/level/level1.png");
            level2 = loader.loadImage("/platformgame/resources/level/level2.png");
        }catch (Exception e){
            System.out.println(e);

        }

        bs = new SpriteSheet(blockSheet);
        ps = new SpriteSheet(playerSheet);

        getTextures();

    }

    private void getTextures() {
        // Blocks
        block[0] = bs.grabImage(1,1,32,32); // dirt
        block[1] = bs.grabImage(2,1,32,32); // grass

        // Player
        player[0] = ps.grabImage(1,1,32,64);                                                       // Idle
        for(int i = 1; i <8;i++) player[i] = ps.grabImage(i+1,1,32,64);                            // Walking right
        for(int i = 1; i <8;i++) player[i+7] = ps.grabImage(i+1,2,32,64);                          // Walk left
        player[15] = ps.grabImage(1,2,32,64);                                                      // Jumping in place
        player[16] = ps.grabImage(1,3,32,64);                                                      // Jumping right
        player[17] = ps.grabImage(2,3,32,64);                                                      // Jumping  left
    }

    // Getters&Setters

    // Level
    public BufferedImage getLevel() { return level; }
    public void setLevel(BufferedImage level) { this.level = level; }
    public BufferedImage getLevel2() { return level2; }
    public void setLevel2(BufferedImage level2) { this.level2 = level2; }
}
