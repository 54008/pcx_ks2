package com.pcx.client.handle;

import com.pcx.message.FindRoomResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class FindHandle extends SimpleChannelInboundHandler<FindRoomResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FindRoomResponseMessage findRoomResponseMessage) throws Exception {
        testController.data.clear();

        Map<String, Integer> res = findRoomResponseMessage.getRes();
        Set<Map.Entry<String, Integer>> entries = res.entrySet();
        for (Map.Entry<String,  Integer>entry:entries){
            Integer value = entry.getValue();
            String key = entry.getKey();
            Vector data = new Vector();
            data.add(key);
            data.add(value);
            testController.data.add(data);
        }
        if (testController.tableModel !=null)
        testController.tableModel.fireTableDataChanged();

    }
}
