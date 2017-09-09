/**
 * Created by shen on 2017/3/1.
 * 验证用户是否登录，如果已登录自动重新登录（token已过期情况下），如果在其他设备修改过密码，对用户进行提示，重新登录
 */
import {$storage_key,$system_key,$tip_message,$interface_url} from '../../common/public-variable'
import {$removeStorage,$setStorage,$getStorage} from '../../common/js/storage'
export function isLogin() {
  let userInfo = $getStorage($storage_key.USERINFO_STORAGE);
  if (userInfo) {
    if (userInfo.timeout&&userInfo.timeout > Date.now()) {
      return true;
    }
    else if(userInfo.account&&userInfo.passWord){
      this.$post($interface_url.login.login, {
        account: userInfo.account.trim(),
        password: userInfo.passWord.trim()
      }, function (data, success) {
        if (success) {
          if (data) {
            let today = new Date()
            data["timeout"] = today.setDate(today.getDate() + 1);//一天过期
            data["password"] = userInfo.passWord.trim();
            $setStorage($storage_key.USERINFO_STORAGE, data);
            return true;
          }
          else {
            return false;
          }
        }
        else {
          alert($tip_message.auto_login_err);
          return false;
        }
      });
    }
    else{
      return false;
    }
  }
  else {
    return false;
  }
}

