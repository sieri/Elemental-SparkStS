package elementalSpark.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;
import elementalSpark.powers.BurnedPower;
import elementalSpark.stances.ElementFireStance;
import elementalSpark.util.TextureLoader;

import static elementalSpark.ElementalSpark.makeRelicOutlinePath;
import static elementalSpark.ElementalSpark.makeRelicPath;

public class UntamedFire extends CustomRelic {

    // ID, images, text.
    public static final String ID = ElementalSpark.makeID("UntamedFire");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("UntamedFire.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("UntamedFire.png"));

    public UntamedFire() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        AbstractPlayer p = AbstractDungeon.player;

        if(newStance instanceof ElementFireStance){

            this.flash();

            addToTop( new ApplyPowerAction(p,p,new BurnedPower(3)));
        }

    }
}
