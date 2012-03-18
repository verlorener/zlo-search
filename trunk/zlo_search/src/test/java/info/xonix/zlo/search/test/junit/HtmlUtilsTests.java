package info.xonix.zlo.search.test.junit;

import info.xonix.zlo.search.utils.HtmlUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author: gubarkov
 * Date: 03.10.2007
 * Time: 22:56:24
 */
public class HtmlUtilsTests {
    @Test
    public void testHtmlUtils() {
        // local:
        assertFalse(HtmlUtils.hasImg("<IMG BORDER=0 SRC=\"pic/bigsmile.gif\" ALT=\":))\">"));
        assertFalse(HtmlUtils.hasImg("<IMG SRC=\"pic/bigsmile.gif\">"));
        // http:
        assertTrue(HtmlUtils.hasImg("<IMG BORDER=0 SRC=\"http://www.ru/pic/bigsmile.gif\" ALT=\":))\">"));
        assertTrue(HtmlUtils.hasImg("<IMG BORDER=0 SRC=\'http://www.ru/pic/bigsmile.gif\' ALT=\":))\">"));
        assertTrue(HtmlUtils.hasImg("<IMG BORDER=0 SRC=http://www.ru/pic/bigsmile.gif ALT=\":))\">"));
        assertTrue(HtmlUtils.hasImg("<IMG BORDER=0 SRC=http://www.ru/pic/bigsmile.gif>"));
        assertTrue(HtmlUtils.hasImg("<IMG SRC=http://www.ru/pic/bigsmile.gif>"));

        String s = "<img src=\"http://rs.foto.radikal.ru/0709/b6/d54aa729b48e.jpg\"><br><br>Арестован после взрыва на Черкизовском рынке.<br><br>Из интервью<br><br><blockquote class=\"quote\"><span class=\"inquote\">[q]</span><b>Цитата:</b><br>— Кем вы приходитесь арестованным ребятам?<br><br>Королёв Никола (26лет), руководитель центра подготовки военно-спортивного клуба СПАС:<br><br>Илья Тихомиров (21 год) — крестник, мой друг.<br><br>Сергей Климук (36лет) — товарищ, прапорщик ФСБ, тренер рукопашного боя в СПАСе.<br><br>Костарев Олег (21год) — ученик СПАСа 2-уровня, бритоголовый.<br><br>Жуковцев Валера (19лет) — ученик СПАСа 1-уровня.<br><br>Никита Синюков (19лет) — мастер спорта по боксу и рукопашному бою, курсант милиции, ученик СПАСа 2-уровня.<br><br>Занимались рукопашным, ножевым боем, частной охранной деятельностью, выпускали газету «Белый Рубеж», создали группу «Белая империя», пытались создать общину.<br><br>— В какой семье вы воспитывались? Какие идеалы вам прививали с детства?<br><br>Я воспитывался в интеллигентной, религиозной семье. Отец — профессор музыки, мать — родовая казачка, старообрядка, бабка — родовая казачка, старообрядка — добрый, святой человек, с детства привила любовь к вере и расе.<br><br>— Кем ребята считали себя: националисты, НС, патриоты?<br><br>Ребята считали себя националистами.<br><br>— Как они пришли к этим идеям?<br><br>К идеи пришли самостоятельно, хотя в СПАСе и проходили политические занятия.<br><br>— Что для них была идеология: мимолётное увлечение или цель жизни?<br><br>Идея святой RaHoWa была целью жизни как минимум у четверых из нас. Я с 1996 года убиваю врагов, был в нескольких правых бригадах, организациях, пока в 2001 году после судимости не ушёл в подполье и не создал СПАС.<br><br>— Как ребята ведут себя уже сейчас, находясь в тюрьме?<br><br>Сергей (Климук), известный в патриотических кругах как «Шаман», будучи в спецтюрьме ФСБ под пытками, чтобы не выдать товарищей, он вскрыл себе вены, после чего от него отстали и перевели на обычную тюрьму. Почти также поступил и Илья (Тихомиров)— он после первых допросов, когда под физическим воздействием рассказал о СПАСе и других её ячейках, взял всю вину на себя и дважды пытался повеситься. Боксёр-Никита (Синюков) сидит в Бутырке, постоянно бреется наголо, когда его бросили к 15 «чёрным» в прессхату, он побил троих, после чего его перевели в другую камеру и теперь его постоянно водят в наручниках.<br><br>— С какой целью был осуществлён взрыв?<br><br>Черкизон — центр нелегальщины, государство в государстве. Ячейка Тихомирова осуществила эту акцию из-за подконтрольности торговли всех рынков оккупантам с юга! Так же хочу заметить, что после этой акции правительство приняло революционные изменения — удаление чужаков с рынков, контроль за эмиграцией МВД и т.д.<br><br>Сейчас на матросске национальные разногласия. 16 сентября «чёрные» убили арестанта. Я сидел с вором Серёгой Самарским — он за славян, и сейчас сижу со смотрящим по тюрьме (славянином), другой претендент — грузин Гия. Практически, матросска разделилась между славянами и «чёрными». Славяне не хотят подчинятся чёрным.<br><br>Я думаю, вы в курсе, что доказано около 15 взрывов — из которых Черкизовский рынок, салон чёрной магии грузинки Лилианы, мечети в Яхроме, казино, пяток палаток, убийство антифа на м. Пушкинская Вигена Абромяна. Так же следствие обладает информацией по другим не доказанным эпизодам — поджёг гей-клуба Тематик, взрыв цыганского дома в Удмуртии, взрывы вьетнамских общаг в 2004-2005 годах, ножевые убийства в электричках и на улицах Москвы, расстрел кавказцев в районе Пролетарки. Все акции наших ячеек были ответом на агрессию со стороны врагов — на Пушке была стрела с афа, на пролетарке стрела с кавказскими бандитами, кавказское казино и общага за сферу влияния по давним просьбам местных жителей.<br><br>На воле остались боевики СПАСа, и в ближайшее время следует ждать новых поджогов и взрывов.<br><br>Мы победим! Слава Руси! Слава Победе! Богу Слава!<br><br>Никола Королёв, 55-ый.<br><br><br>Вопросы задавала Александра Волкова<br>Фото предоставлены сестрой Николы Королёва<br><br><br><br><br><br>Этапы пути Николы Королёва<br><br>В 1997 году вошёл в бригаду «Жилдор», сделав бригадную tatu — германского орла на правом плече. На тот момент имел тройные белые шнурки.<br><br>В 1998 году — поджог кавказского общежития на Ясном проезде.<br><br>В 1998 участвует в создании бригады Streethools.<br><br>В 1999 году взорвал палатку чечена на Белорусской.<br><br>В 1999 году в составе «Объединенной Бригады 88» участвует в нападении на афраконцерт в Д.К, «Крылья Советов». Ездит на выезды в Питер, Воронеж, Киев, Германию.<br><br>В 2000 году арестован в результате спецоперации Птеровки38 арестован за терроризм. Доказаны только изготовление и хранение оружия — 222 ст. Осуждён на 3 года. Вышел по амнистии на условный срок.<br><br>В 2001 году для конспиративного обучения молодёжи создал центр военно-спортивной подготовки — СПАС. Его ребята участвовали в погромах совместно с Объединёнными бригадами в Ясенево, Царицыно, на Манежной площади.<br><br>Ученики и друзья Николы Королёва провели следующие диверсионные акции:<br><br>2003 год, лето — взрыв кавказской палатки.<br><br>2003 год, ноябрь — поджёг общежития Патриса Лулумбы.<br><br>2004 год — взрыв вьетнамского общежития.<br><br>2005 год — взрыв вьетнамского общежития Красная река.<br><br>2006 год — взрыв мечети, четырёх кавказских палаток, кавказские общежития с казино на Старообрядческой улице, взрыв игровых автоматов, взрыв салона чёрной магии, поджёг гей-клуба «Тематик».<br><br>21 августа 2006 года — взрыв на Черкизовском рынке.<br><br>После ареста Николы Королёва акции продолжаются:<br><br>В 2007 году сожжён дом американской секты в Солнцево, который спасовцы также поджигали и в 2006 году.<span class=\"inquote\">[/q]</span></blockquote><br><br><blockquote class=\"quote\"><span class=\"inquote\">[q]</span><b>Цитата:</b><br> Уроки русского сопротивления<br>Дело группы Николая Королева<br><br>После принятия серии новых законов «О борьбе с экстремизмом» в России была легализована связь правоохранительных органов с «общественными» организациями активно борющимися с русским населением. С этого момента практически все этнические группировки (грузинские, еврейские, армянские, азербайджанские…) смогли открыто общаться с сотрудниками МВД, ФСБ по вопросам преследования русских граждан по принципу их личных взглядов и активной жизненной позиции. Как обычно под закон на практике попало только русское население. Любой русский человек, названный представителем этнического меньшинства лидером экстремистского движения, автоматически подпадал под действие этого закона. Для этого не требовалось ни санкции суда, ни санкции прокуратуры. Защититься от клеветы законным способом было так же невозможно, т.к. информация подобного рода передавалась конфиденциально.<br><br>Разработчик закона «О борьбе с экстремизмом» предложил выявлять на ранней стадии и дискредитировать наиболее активных распространителей национальных идей. Формы дискредитации в законе не уточнялись.<br><br>Подобные формы воздействия проводившиеся ранее армянской, еврейской и чеченской диаспорами были не профессиональными и не всегда приносили положительный эффект. Черных все чаще били, резали, иногда стреляли.<br><br>А работа стражей порядка обещала повысить результативность подобных акций, уменьшить расходы криминальных диаспор и снизить риск для своих членов (в случае поимки им грозила статья за хулиганство, причинение тяжелых телесных повреждений, убийство), а тут все официально.<br><br>«Офицеры» МВД и ФСБ пользуясь своими базами данных и наработками быстро определяли круг лиц, интересующий авторитета, а затем не просто сдавали ему наиболее активных граждан РФ, но даже предлагали провести необходимый курс профилактических мероприятий. Иногда эти операции проводились совместно с членами диаспор, иногда работа сдавалась оперативниками МВД и ФСБ «под ключ». Службы безопасности МВД, ФСБ сделать ничего не могли, даже если бы хотели – все действия проводились в соответствии с Законом «О борьбе с экстремизмом».<br><br>В столице с 2002 года стала появляться информация о том, что на лидеров националистических организаций под разными предлогами нападают бригады людей славянской наружности, спортивного телосложения. Что крайне удивляло – это характерная тактика действия групп, организованность, активное применение приемов самбо. Бандиты – это были оперативники ФСБ или МВД (никто из пострадавших не мог точно сказать).<br><br>Не смотря на хорошую подготовку подобные операции не всегда заканчивались удачно, в нескольких случаях «бойцы невидимого фронта» получали по шее. На юге Москвы одна из групп, при нападении на славянскую молодежь, была просто зарезана. Но, к сожалению, из-за особенности той ситуации определить ведомственную принадлежность хулиганов (обыскать) просто не успели. Для правовой формы борьбы против произвола МВД-ФСБ требовались конкретные «тушки», с документами и в составе своей группы. Об оперативниках было известно, что они работают как по конкретной наводке, так и в составе «летучих бригад» по зачистке определенных территорий….<br><br>Летом 2002 года Николай Королев вместе с группой детей (членов военно-спортивного клуба) вышел для тренировочного похода на р.Яуза. Группа была снабжена документами управления образования. Николай имел при себе удостоверение педагога дополнительного образования и маршрутный лист. В группе находились дети 10-16 лет, аккуратно подстриженные и одетые в военизированную форму. Группа разместилась на берегу реки на видном месте (для отдыха). Ждать оперов пришлось не долго. Надо признать, что основной целью «бойцов невидимого фронта» были не дети, а их руководитель – Королев Николай. Опера думали, что вшестером они легко могут избить и унизить худощавого русского учителя, при этом дети получат хороший урок и больше не будут ходить на занятия к этому преподавателю. Играли опера хорошо: золотые цепи, уголовный сленг – все это должно было выглядеть устрашающе. С криками «фашистские ублюдки», «русские свиньи», мы вас сейчас здесь…, строиться суки по одному, росиянские герои смело бросились на 12-летнего коротко стриженного парня. Никола попытался словами успокоить «уголовников» объясняя, что перед ними дети, в ответ двухметровый антифашист с размаху ударил ребенка, прикрывая молодняк собой Никола принял бой, а т.к . он не знал целей преступников (может быть это были маньяки, охотящиеся за русскими детьми), Никола действовал холодно и решительно. Первым упал двухметровый урод (4 удара ножом в корпус), он даже не понял, что произошло. Второй оперативник хорошо отработанным приемом перехватил руку Николы, пытаясь вывести ее на болевой, но тут же ощутил удар в корпус и увидел, что в удерживаемой руке ножа нет, с криком, ничего не понимая самбист кувырком ушел в сторону. Старший группы, пытаясь провести захват ног почувствовал лезвие ножа у своего горла, остановился, поняв, что он уже в заложниках, по щеке текла кровь, через секунду помятый коленом капитан видел улыбающиеся лицо Николы и кусок мяса в его руке, еще через мгновение он ощутил отсутствие своего уха. Все проходило как в каком-то ужасном сне. Милиционеры делали все правильно, как их учили на уроках боевого самбо. Они провели сотни задержаний и ни разу не было осечки, а тут какой-то щуплый парень, 4 секунды – и партия закончена, старший группы в заложниках. Трое оставшихся оперативников не знали что делать, на задание они вышли без табельного оружия, корочки светить они тоже не могли – миссия неофициальна, а погибать от ножа «русского фашиста» им тоже не хотелось, об этом в американских фильмах ничего не показывали, к тому же самые сильные и опытные коллеги уже лежали.<br><br>У детей была обратная реакция, они увидели пример того, что должен делать перед лицом опасности русский человек. Они превратились в настоящих граждан, понимающих, что за свободу надо бороться. Через 20 минут на дороге группу школьников остановила патрульная машина – ребят задержали. Никола предъявил сотрудникам милиции бумаги управления образования, на основании которых он проводил полевой выход. Ухо оперативника вернули хозяину. В связи с тем, что опера напали на организованную группу школьников и их педагога, а не как им сказали «на разрозненную группу скинхедов и их лидера» – уголовное дело возбудили против сотрудников милиции, вся группа была уволена из органов задним числом. Боец, находящийся в реанимации (4 ножевых ранения), даже не получил положенных «шекелей». И раввин с начальником к нему в больницу не пришли – он живой. Учитель Николай Королев был официально награжден медалью за гражданскую доблесть.<br><br>Этот случай был третьим за 4 месяца 2002 года, когда в Москве и ближайшем Подмосковье оперативные группы несли потери в ножевом бою. Но именно в этот раз вся группа была полностью засвечена. Повтор подобных инцидентов мог бы привести уже к скандалу, к тому же выявилась слабость схемы:<br><br>1. Физическое и численное преимущество ничего не давало, когда сотрудники сталкивались на улицах с бойцами из школ боевого фехтования.<br><br>2. Сотрудники, в случае реального боя не могли применять табельное оружие, их миссия конфиденциальна.<br><br>3. Применение техники самбо показало свою неэффективность в столкновении с русскими национальными школами ножевого боя.<br><br>4. В случае задержания участников другой группой МВД, либо передачи дела в прокуратуру силовики оказывались в неприятной ситуации, а «русские фашисты» могли использовать различные формы самозащиты, пользуясь правом на самооборону<br><br>Так или иначе с 2002 года массовая практика использования силовых структур для нападения на представителей русского национального движения была прекращена.<br><br><a href=\"http://www.nordrus.org/news/detail.php?ID=1677\" style=\"text-decoration: underline;\" target=\"_blank\">http://www.nordrus.org/news/detail.php?ID=1677</a><span class=\"inquote\">[/q]</span></blockquote><br><br>PS Интервью с такими подробностями было дано по всей видимости из-за того, что ребятам уже практически нечего терять, сроки огромные.<br><br>Слава героям!";
        assertFalse(HtmlUtils.hasUrl("234"));
        assertTrue(HtmlUtils.hasImg(s));
        assertTrue(HtmlUtils.hasUrl(s));

        assertTrue(HtmlUtils.hasImg("<IMG src = \"http://194.85.81.128/~mc/pics/mybike.jpg\">"));
        assertTrue(HtmlUtils.hasImg("<IMG src = https://194.85.81.128/~mc/pics/mybike.jpg>"));
        assertFalse(HtmlUtils.hasImg("<IMG src = /pics/mybike.jpg>"));

        assertEquals("a\"a", HtmlUtils.unescapeHtml("a&quot;a"));
        assertEquals("<qqq>", HtmlUtils.unescapeHtml("&lt;qqq&gt;"));
        assertEquals("1&&3", HtmlUtils.unescapeHtml("1&amp;&amp;3"));

        String gamesHost = "games.mipt.ru";
        assertTrue(HtmlUtils.hasImg("<img src='http://123' >", gamesHost));
        assertTrue(HtmlUtils.hasImg("<img src=\"http://123\" >", gamesHost));
        assertTrue(HtmlUtils.hasImg("<img src=https://123 >", gamesHost));
        assertTrue(HtmlUtils.hasImg("<img src=https://123 />", gamesHost));
        assertTrue(HtmlUtils.hasImg("<img src=https://123 / >", gamesHost));

        assertFalse(HtmlUtils.hasImg("<img src=http://games.mipt.ru/cstrike/board/pic/123.jpg / >", gamesHost));
        assertFalse(HtmlUtils.hasImg("<img src=\"http://games.mipt.ru/cstrike/board/pic/123.jpg\" / >", gamesHost));
        assertFalse(HtmlUtils.hasImg("<img src='https://games.mipt.ru/cstrike/board/pic/123.jpg' / >", gamesHost));

        assertTrue(HtmlUtils.hasImg("<img src='https://games1.mipt.ru/cstrike/board/pic/123.jpg' / >", gamesHost));
        assertTrue(HtmlUtils.hasImg("<img src='https://games1.mipt.ru/cstrike/board/pic/123.jpg' / >  " +
                "123123<img src='https://games.mipt.ru/cstrike/board/pic/123.jpg' / >", gamesHost));
        assertTrue(HtmlUtils.hasImg("<img src='https://games.mipt.ru/cstrike/board/pic/123.jpg' / > " +
                "<img src='https://games1.mipt.ru/cstrike/board/pic/123.jpg' / >", gamesHost));
        assertFalse(HtmlUtils.hasImg("<img src='https://games.mipt.ru/cstrike/board/pic/123.jpg' / > " +
                "<img src='https://games.mipt.ru/cstrike/board/pic/222.jpg' / >", gamesHost));
        assertTrue(HtmlUtils.hasImg(
                "<img src='https://games.MIPT.ru/cstrike/board/pic/111.jpg' / > " +
                        "<img src =\"https://mipt.RU/cstrike/board/pic/222.jpg\" />" +
                        "<  img src = https://gameS.mipt.ru/333.jpg   />", gamesHost));
        assertFalse(HtmlUtils.hasImg(
                "<img src='HTTPS://gamES.mipt.ru/cstrike/board/pic/111.jpg' / > " +
                        "<img sRc =\"https://games.mipt.ru/cstrike/board/pic/222.jpg\" />" +
                        "<  imG src = https://games.MIPT.ru/333.jpg   />", gamesHost));
    }

    @Test
    public void testHtmlUtils_extractUrls() {
        assertEquals("aaa bbb", HtmlUtils.extractUrls("<A HrEf=\"aaa\">bbb"));
        assertEquals("aaa bbb", HtmlUtils.extractUrls("<a \t\t href='aaa' \t \t>bbb"));
        assertEquals("http://yandex.ru bbb", HtmlUtils.extractUrls("<a \t\t href=\"http://yandex.ru\" \t \t>bbb"));
        assertEquals("https://qqq.qq.q/q?qq=qqq bbb", HtmlUtils.extractUrls("<A \t\t HREF='https://qqq.qq.q/q?qq=qqq' \t \t>bbb"));
        assertEquals("http://board.rt.mipt.ru/?read=1542955 ссылка QQQ zzz",
                HtmlUtils.cleanHtml("<A \n \t\n HREF=\"http://board.rt.mipt.ru/?read=1542955\" >ссылка</a> <b>QQQ<i>zzz</i></b>")
                        .replaceAll("\\s+", " ")
                        .trim()
        );
    }


}
