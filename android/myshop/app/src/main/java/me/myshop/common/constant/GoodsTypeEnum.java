package me.myshop.common.constant;

public enum GoodsTypeEnum {
    CPU("1"),GPU("2"),RAM("3"),DISK("4");

    private String index;

    GoodsTypeEnum(String index) {
        this.index = index;
    }

    public static GoodsTypeEnum compare(String goodsType) {
        if (GoodsTypeEnum.CPU.toString().equals(goodsType)) {
            return GoodsTypeEnum.CPU;

        } else if (GoodsTypeEnum.GPU.toString().equals(goodsType)) {
            return GoodsTypeEnum.GPU;

        } else if (GoodsTypeEnum.RAM.toString().equals(goodsType)) {
            return GoodsTypeEnum.RAM;

        } else {
            return GoodsTypeEnum.DISK;
        }

    }


    @Override
    public String toString() {
        return index;
    }
}
