package cn.loach.server.handler;

import cn.loach.server.message.response.LoginAuthResponseMessage;
import cn.loach.server.message.Message;
import cn.loach.server.message.request.RequestMessage;
import cn.loach.server.session.SessionContainer;
import cn.loach.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

public class LoginAuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        RequestMessage msg1 = (RequestMessage) msg;
//        if (msg1.getMessageRequestTypeType() == Message.LOGIN_AUTH_MESSAGE_REQUEST_TYPE) {
//            LoginAuthResponseMessage loginAuthResponseMessage = (LoginAuthResponseMessage) msg1;
            // 注册
            String messageFromId = msg1.getFromId();
            String authToken = UUID.randomUUID().toString();

            if (StringUtil.isEmpty(messageFromId, authToken)) {
                //处理 登录失败
//                ctx.writeAndFlush(error());
                return;
            }

            //TODO 验证TOKEN是否正确

            if (!SessionContainer.set(messageFromId, authToken, ctx)) {
//                ctx.writeAndFlush(error());
//                return;
            }
//
//            ResponseMessage responseMessage = ResponseMessage.success(200, "Auth Success");
//            ctx.writeAndFlush(responseMessage);
        /*}*//*else {
            RequestMessage requestMessage = (RequestMessage) msg;
            // 验证token
            if (StringUtil.isEmpty(requestMessage.getAuthToken())) {
//                ctx.writeAndFlush(error());
                return;
            }

            // 根据token获取用户Id
            String userId = SessionContainer.getUserIdByToken(requestMessage.getAuthToken());
            if (StringUtil.isEmpty(userId)) {
//                ctx.writeAndFlush(error());
                return;
            }

            if (!userId.equals(requestMessage.getFromId())) {
//                ctx.writeAndFlush(error());
                return;
            }

            // 验证通过 递给下个业务通道
            ctx.fireChannelRead(msg);
        }

        super.channelRead(ctx, msg);
    */
        ctx.fireChannelRead(msg);
    }

//    private ResponseMessage error() {
//        return ResponseMessage.error(501, "Auth Error");
//    }
}
