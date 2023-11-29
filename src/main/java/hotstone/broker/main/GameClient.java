package hotstone.broker.main;

import frds.broker.Requestor;
import frds.broker.ipc.http.UriTunnelClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.common.BrokerConstants;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaGameFactory;
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import hotstone.view.tool.DualUserInterfaceTool;
import hotstone.view.tool.HotSeatStateTool;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

public class GameClient {
    public static void main(String[] args)  {
        String host = "localhost";
        Player whoToPlay = Player.FINDUS;
        String gameid = GameClientProxy.singletonID;

        // Create the client side Broker roles
        UriTunnelClientRequestHandler clientRequestHandler
                = new UriTunnelClientRequestHandler(host, BrokerConstants.HOTSTONE_PORT,
                false, BrokerConstants.HOTSTONE_TUNNEL_PATH);
        Requestor requestor = new StandardJSONRequestor(clientRequestHandler);

        Game game = new GameClientProxy(requestor);


        DrawingEditor editor =
                new MiniDrawApplication( "HotSeat: Variant " + "semi",
                        new HotStoneFactory(game, Player.FINDUS,
                                HotStoneDrawingType.OPPONENT_MODE) );
        editor.open();

        editor.setTool(new DualUserInterfaceTool(editor, game, whoToPlay));
    }
}
