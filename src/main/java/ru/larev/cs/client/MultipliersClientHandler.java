package ru.larev.cs.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
public class MultipliersClientHandler extends ChannelInboundHandlerAdapter {
    private Scanner scanner = new Scanner(System.in);

    private int getInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeInt(getInt());
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bb = (ByteBuf) msg;
        IntStream.range(0, (bb.readableBytes() / 4)).forEach(value -> {
            System.out.println(bb.readInt());
        });
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeInt(getInt());
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
