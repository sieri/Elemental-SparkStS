package elementalSpark.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import elementalSpark.stances.ElementAbstractStance;
import elementalSpark.util.TextureLoader;

import static elementalSpark.ElementalSpark.makePowerPath;

public class StopEarthPower extends AbstractStopElementPower {

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("StopEarthPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("StopEarthPower32.png"));

    public StopEarthPower(int amount) {
        super(amount, ElementAbstractStance.ElementType.Earth);

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }
}
