/**
 * Created by shen on 2017/3/6.
 * 本地缓存，通过公钥和私钥进行加密和解密
 */
import {md5} from 'vux'
export function getStorage(key) {
  var publicString = "-----BEGIN PUBLIC KEY-----\n" +
    'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIWv2HL3BK1m13RG7GU2hw3DKCr/gbC+\n' +
    'Z1ignI7KEGeoMGMh/gThyN3mwH09YZq+6kxS7yC5C8ejCaw1U6/+3sMCAwEAAQ==\n' +
    '-----END PUBLIC KEY-----';
  var NodeRSA = require('node-rsa');
  var rsaKey = new NodeRSA();
  rsaKey.importKey(publicString, 'pkcs8-public');
  key=md5(key);
  let value =localStorage.getItem(key);

  if(value) {
    value = rsaKey.decryptPublic(value,'utf8');
    value = JSON.parse(value);
    return value;
  }
  else
  {
    return null;
  }
}
export function setStorage(key,value) {
  var NodeRSA = require('node-rsa');
  var keyString = "-----BEGIN PRIVATE KEY----- \
MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAha/YcvcErWbXdEbs \
ZTaHDcMoKv+BsL5nWKCcjsoQZ6gwYyH+BOHI3ebAfT1hmr7qTFLvILkLx6MJrDVT \
r/7ewwIDAQABAkAsKtCECwBMmbGNBPGM7trnvRZEPm4GiqFyMn4G3tNUq0ikpVDh \
0F5zzZ9nGEocwxPpYlj7cdnzBubElv4sLW6hAiEA+dW530+qNOaoEnj8QzDrfnGt \
VpV1ZTiGyJZRp7cXIX8CIQCI/GGrgtWtXENS67ZwmN5ux+RTkZ2KB9c7Ulti6d7c \
vQIhAKtNKE09ym7LWKKR4iYP/OiN+VRM1lm5EHGo4AQnPezxAiBVJhQMCVKK5RvQ \
EkagLNWupL/vlkcHwqHt9N5rWJY4PQIgMud8O5sgn78G74g9kqgChYnp9gurQwbS \
f0gXJ1B92uQ= \
-----END PRIVATE KEY-----";
  var rsaKey = new NodeRSA(keyString);
  key=md5(key);
  localStorage.setItem(key,rsaKey.encryptPrivate(JSON.stringify(value),'base64'));
}
export function removeStorage(key) {
  localStorage.removeItem(md5(key));
}
export function clearStorage() {
  window.localStorage.clear();
}



export function rsaDes(text) {
  var NodeRSA = require('node-rsa');

 // var text = 'Hello RSA!';

  var keyString = "-----BEGIN RSA PRIVATE KEY----- \
  MIICXwIBAAKBgQDXTkg2Y58jausrIUOlyWY6xRy0Fdjyp/E8J1mtBLeUAVxOE+vv \
  IOucBu8kqzxPFCv7TcaM7QRbsuQ9MVOCuJE6XdCArZFEuYwhwNBQ/iAQsm6OPstd \
  4kMkuKOVqnHimITQKl5dHBLBLE3ICJTwpUw0OB7vdvfNTHsCouQR5Z5j4QIDAQAB \
  AoGBAMijGogUrefDawL2LKQB2lJJ/UWUVmdKOXNk+BxTmP9DhVD9H4/njagIjjIN \
  oFXQ5mt5Pt10werx+d9MaBYZCLs0OJhMDsFf88z23jwKurAPzFJr5n00n4V5IoKQ \
  i4PvhJ4MsX/o1aWjcXniDPx3ZX/B3KjPxYxeADeYYztV9TMBAkEA/0ZzQQ9qciJH \
  jLWh0DVY1xExLTvJz6hR44+o06UtjuKUNLpd87D5VYh0MAa2hlbeSiBff3V74Q1X \
  G0/gfCx8ZQJBANfqx5YPYLB86abPsIzbg/gVytM3i+krlPWBZhX8cOq/FXTwM1xW \
  Lyc5GUfLEHP+KKCt9/ASQ1IGF88Yb64Nu80CQQD8h3T8p4Nl5QNi70tj7WI1X4yG \
  ct7xzlE0DKm2tXCcKQj0IGlQ6YG+W36VBA8MVmMQcXYHYPFrcQb6vcoVDO0BAkEA \
  j+yC78b+V4ZcJQJKGAp3FFZKZbKgA2Q2ndOMXdlw1zlaLyGxyrn0InwQGugD5p2j \
  LT/phlBWKYXANx59yxKCfQJBAJ3HeeqF7HCGGCMzgtEU74czalQ3pp//a5mtPkk4 \
  TOZekCXPsEECRR5YUPabTVY7f3W3LkbqNTrGUHxktrsapjo= \
    -----END RSA PRIVATE KEY-----";

  var key = new NodeRSA(keyString,{b:1024});


  var encrypted = key.encryptPrivate(text, 'base64');
  console.log('encrypted: ', encrypted);

  var privateKey = key.exportKey('pkcs8');
  console.log('privateKey:', privateKey);

  var publicKey = key.exportKey('pkcs8-public');
  console.log('publicKey:', publicKey);


  var publicString = "-----BEGIN PUBLIC KEY-----\n" +
    'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXTkg2Y58jausrIUOlyWY6xRy0\n' +
    'Fdjyp/E8J1mtBLeUAVxOE+vvIOucBu8kqzxPFCv7TcaM7QRbsuQ9MVOCuJE6XdCA\n' +
    'rZFEuYwhwNBQ/iAQsm6OPstd4kMkuKOVqnHimITQKl5dHBLBLE3ICJTwpUw0OB7v\n' +
    'dvfNTHsCouQR5Z5j4QIDAQAB\n' +
    '-----END PUBLIC KEY-----';

  var key2 = new NodeRSA();
  key2.importKey(publicString, 'pkcs8-public');

  var sign = key.sign(text, 'base64');
  console.log('sign:', sign);

  var decrypted = key2.decryptPublic(encrypted, 'utf8');
  console.log('decrypted: ', decrypted);

  var verify = key2.verify(decrypted, sign, '', 'base64');
  console.log('verify', verify);
}



export default {
  install (Vue) {
    Vue.prototype.$getStorage = getStorage
    Vue.prototype.$setStorage = setStorage
    Vue.prototype.$removeStorage = removeStorage
    Vue.prototype.$clearStorage = clearStorage
  },
  $getStorage: getStorage,
  $setStorage:setStorage,
  $removeStorage:removeStorage,
  $clearStorage:clearStorage,
}

export const $getStorage = getStorage
export const $setStorage = setStorage
export const $removeStorage = removeStorage
export const $clearStorage = clearStorage
