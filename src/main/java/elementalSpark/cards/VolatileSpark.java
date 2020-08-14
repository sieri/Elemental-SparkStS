package elementalSpark.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ChangeElementalStanceAction;
import elementalSpark.actions.ChooseElementAction;
import elementalSpark.characters.TheSpark;
import elementalSpark.stances.ElementAbstractStance;
import elementalSpark.stances.ElementWaterStance;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static elementalSpark.ElementalSpark.makeCardPath;


public class VolatileSpark extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(VolatileSpark.class.getSimpleName());
    public static final String IMG = makeCardPath("VolatileSpark.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;


    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;

    // /STAT DECLARATION/


    public VolatileSpark() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded)
        {
            AbstractDungeon.actionManager.addToBottom(
                    new ChooseElementAction()
            );

        }
        else
        {
            AbstractDungeon.actionManager.addToBottom(
                    new ChangeElementalStanceAction()
            );
        }


    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
