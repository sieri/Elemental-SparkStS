package elementalSpark.cards;

import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ConvertToRockShieldAction;
import elementalSpark.characters.TheSpark;
import elementalSpark.modifiers.ElementLimiterModifier;
import elementalSpark.stances.ElementAbstractStance;
import elementalSpark.stances.ElementAirStance;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static elementalSpark.ElementalSpark.makeCardPath;


public class Harden extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(Harden.class.getSimpleName());
    public static final String IMG = makeCardPath("Harden.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;


    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int BLOCK = 0;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    // /STAT DECLARATION/


    public Harden() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        block = BLOCK;

        CardModifierManager.addModifier(this, new ElementLimiterModifier(ElementAbstractStance.ElementType.Earth));
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(block > 0)
        {
            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(p, block)
            );
        }

        AbstractDungeon.actionManager.addToBottom(
                new ConvertToRockShieldAction()
        );
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            upgradeBaseCost(UPGRADED_COST);
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
