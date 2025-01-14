package cn.loach.server.protocol;

import cn.loach.server.message.Message;
import cn.loach.server.serializable.LoachSerializable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class MessageDecoder extends ByteToMessageDecoder {



    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) {
        int magicNum = in.readInt();

        byte version = in.readByte();

        byte serializerType = in.readByte();

        int messageRequestTypeType = in.readInt();

        byte[] idByte = new byte[32];
        in.readBytes(idByte);

        String messageId = new String(idByte, StandardCharsets.UTF_8);

        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);

        Message message = LoachSerializable
                .getSerializable(serializerType)
                .deserialize(Message.messageClassMap.get(messageRequestTypeType), bytes);

        list.add(message);
    }
}
