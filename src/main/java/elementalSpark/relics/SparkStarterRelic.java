package elementalSpark.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ChangeElementalStanceAction;
import elementalSpark.util.TextureLoader;

import static elementalSpark.ElementalSpark.makeRelicOutlinePath;
import static elementalSpark.ElementalSpark.makeRelicPath;

public class SparkStarterRelic extends CustomRelic {

    // ID, images, text.
    public static final String ID = ElementalSpark.makeID("SparkStarterRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SparkStarterRelic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("SparkStarterRelic.png"));

    public SparkStarterRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        addToTop(new ChangeElementalStanceAction());
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
