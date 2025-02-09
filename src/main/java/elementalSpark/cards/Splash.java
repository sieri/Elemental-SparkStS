package elementalSpark.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;
import elementalSpark.characters.TheSpark;
import elementalSpark.stances.ElementAbstractStance;

import static elementalSpark.ElementalSpark.makeCardPath;


public class Splash extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(Splash.class.getSimpleName());
    public static final String IMG = makeCardPath("Splash.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;

    private static final int DAMAGE = 1;
    private static final int UPGRADE_PLUS_DMG = 2;

    // /STAT DECLARATION/


    public Splash() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void triggerExhaustedCardsOnStanceChange(AbstractStance newStance) {
        if(ElementAbstractStance.getType(newStance) == ElementAbstractStance.ElementType.Water)
        {
            this.addToBot(new DiscardToHandAction(this));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
