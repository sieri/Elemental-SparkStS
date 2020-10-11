package elementalSpark.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ChangeElementalStanceAction;
import elementalSpark.powers.BurnedPower;
import elementalSpark.stances.ElementAbstractStance;
import elementalSpark.stances.ElementEarthStance;
import elementalSpark.stances.ElementFireStance;
import elementalSpark.util.TextureLoader;

import static elementalSpark.ElementalSpark.makeRelicOutlinePath;
import static elementalSpark.ElementalSpark.makeRelicPath;

public class MagicRock extends CustomRelic {

    // ID, images, text.
    public static final String ID = ElementalSpark.makeID("MagicRock");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MagicRock.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MagicRock.png"));

    public MagicRock() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if(newStance instanceof ElementEarthStance){
            AbstractPlayer p = AbstractDungeon.player;

            this.flash();

            addToTop( new GainBlockAction(p, 3));
        }
    }
}
