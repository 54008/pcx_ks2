package com.pcx.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class ProcotolFrameDecode extends LengthFieldBasedFrameDecoder {
    public ProcotolFrameDecode(){
        this(1024,12,4,0,0);
    }
    public ProcotolFrameDecode(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}
