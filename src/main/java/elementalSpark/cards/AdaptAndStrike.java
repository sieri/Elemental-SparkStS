package elementalSpark.cards;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ChangeElementalStanceAction;
import elementalSpark.characters.TheSpark;
import elementalSpark.modifiers.BasicAdaptiveAttackModifier;
import elementalSpark.stances.ElementAbstractStance;

import static elementalSpark.ElementalSpark.makeCardPath;


public class AdaptAndStrike extends AbstractDynamicCard implements ElementAbstractStance.Sub {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(AdaptAndStrike.class.getSimpleName());
    public static final String IMG = makeCardPath("AdaptAndStrike.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC_PLUS = 1;

    // /STAT DECLARATION/


    public AdaptAndStrike() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        damage = DAMAGE;
        magicNumber = MAGIC;
        baseMagicNumber = magicNumber;

        tags.add(CardTags.STRIKE);
        CardModifierManager.addModifier(this, new BasicAdaptiveAttackModifier( 3));
        ElementAbstractStance.subscribe(this);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        ElementAbstractStance.unsubscribe(this);
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADE_MAGIC_PLUS);
            initializeDescription();
        }
    }

    @Override
    public void notify(ElementAbstractStance.ElementType element) {
        if(AbstractDungeon.player.hand.contains(this)) {
            isDamageModified = true;
            upgradeDamage( magicNumber);
        }
    }
}
