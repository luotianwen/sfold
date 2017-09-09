/**
 * Created by shen on 2017/3/6.
 */
import  * as types from './mutation-types'


export const addToCart = ({commit}, good) => {
  commit(types.ADD_TO_CART, {good});
};
export const deleteToCart = ({commit}) => {
  commit(types.DELETE_TO_CART);
};
export const addNumberToCart = ({commit}, good) => {
  commit(types.ADD_NUMBER_TO_CART, {good});
};
export const selectedGood = ({commit}, good) => {
  commit(types.SELECT_GOOD, {good});
};
export const selectedShop = ({commit}, shopid) => {
  commit(types.SELECT_SHOP, {shopid});
};
export const selectedAllGood = ({commit},val) => {
  commit(types.SELECT_ALL_GOODS,{val});
};

export const syncCartInfo=({commit},cartInfo)=>{
  commit(types.SYNC_CARTINFO,{cartInfo});
}
export const deleteGood=({commit},good)=>{
  commit(types.DELETE_GOOD,{good});
}
