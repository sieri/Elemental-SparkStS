package elementalSpark.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import elementalSpark.ElementalSpark;

import java.util.LinkedList;


public abstract class ElementAbstractStance extends AbstractStance {

    public enum ElementType {Fire, Water, Air, Earth, None}

    public ElementType type;

    private static long sfxId = -1L;

    public final String loopKey;

    public static ElementType getType(AbstractStance stance)
    {
        if(stance instanceof ElementAbstractStance)
        {
            return ((ElementAbstractStance) stance).type;
        }
        return  ElementType.None;
    }


    public ElementAbstractStance(String loopKey)
    {
        this.loopKey = loopKey;
        this.img = ImageMaster.loadImage("elementalSparkResources/images/char/SparkCharacter/orb/layer3.png");
    }

    @Override
    public void onEnterStance() {
        if (sfxId != -1L) {
            this.stopIdleSfx();
        }

        CardCrawlGame.sound.play("BELL");
        sfxId = CardCrawlGame.sound.playAndLoop(loopKey);
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(this.c, true));
        //TODO:Add effects for start up
        // AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Divinity"));


        updateCards();
    }

    @Override
    public void onExitStance() {
        super.onExitStance();

        if( !(AbstractDungeon.player.stance  instanceof ElementAbstractStance))
        {
            updateCards();
        }
    }

    //Update cards description that could be changed with a card.
    private void updateCards()
    {

        for (Sub sub:subs) {
            sub.notify(type);
        }

        ElementalSpark.logger.debug("Update Elements");

        for (AbstractCard card : AbstractDungeon.player.hand.group) {

            card.initializeDescription();

        }


    }

    public void stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop(loopKey, sfxId);
        }
        sfxId = -1L;
    }



    //Subscription system for change needed when the stances change
    public interface Sub{
        void notify(ElementType element);

    }

    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.2F;
                AbstractDungeon.effectsQueue.add(new DivinityParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Divinity"));
        }

    }



    private static final LinkedList<Sub> subs = new LinkedList<Sub>();

    public static void subscribe(Sub s)
    {
        subs.add(s);
    }

    public static void unsubscribe(Sub s)
    {
        subs.remove(s);
    }
}
