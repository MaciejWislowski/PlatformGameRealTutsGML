package platformgame.graphics;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs, ps;

    private BufferedImage blockSheet = null;
    private BufferedImage playerSheet = null;

    public BufferedImage[] block = new BufferedImage[2];
    public BufferedImage[] player = new BufferedImage[15];

    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            blockSheet = loader.loadImage("/platformgame/resources/object/block.png");
            playerSheet = loader.loadImage("/platformgame/resources/object/player.png");
        }catch (Exception e){
            System.out.println(e);;

        }

        bs = new SpriteSheet(blockSheet);
        ps = new SpriteSheet(playerSheet);

        getTextures();
    }

    private void getTextures() {
        block[0] = bs.grabImage(1,1,32,32); // dirt
        block[1] = bs.grabImage(2,1,32,32); // grass

        // Player
        // Idle
        player[0] = ps.grabImage(1,1,32,64);
        // Walk right
        for(int i = 1; i <8;i++){
            player[i] = ps.grabImage(i+1,1,32,64);
        }
        // Walk left
        for(int i = 1; i <8;i++){
            player[i+7] = ps.grabImage(i+1,2,32,64);
        }

    }
}
