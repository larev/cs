package ru.larev.cs.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
@ChannelHandler.Sharable
public class MultipliersServerHandler extends ChannelInboundHandlerAdapter {
    private void multipliers(int n, ByteBuf buffer) {

        if (n <= 1)
            return;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                n = n / i;

                if (n > 1) {
                    buffer.writeInt(i);
                    multipliers(n, buffer);
                    return;
                } else {
                    buffer.writeInt(i);
                    return;
                }
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bb = (ByteBuf) msg;
        int i = bb.readInt();
        ByteBuf buffer = ctx.alloc().buffer();
        multipliers(i, buffer);
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
