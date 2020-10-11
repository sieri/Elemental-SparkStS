package elementalSpark.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ChangeElementalStanceAction;
import elementalSpark.stances.ElementAbstractStance;
import elementalSpark.stances.ElementEarthStance;
import elementalSpark.util.TextureLoader;

import static elementalSpark.ElementalSpark.makeRelicOutlinePath;
import static elementalSpark.ElementalSpark.makeRelicPath;

public class FeatherArmor extends CustomRelic {

    // ID, images, text.
    public static final String ID = ElementalSpark.makeID("FeatherArmor");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("FeatherArmor.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("FeatherArmor.png"));

    public FeatherArmor() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        if(damageAmount > AbstractDungeon.player.currentBlock &&
                    new Random().random(0,1) == 1)  //50% chance
        {
            addToTop(new ChangeElementalStanceAction(ElementAbstractStance.ElementType.Air));
        }

        return super.onAttacked(info, damageAmount);
    }
}
