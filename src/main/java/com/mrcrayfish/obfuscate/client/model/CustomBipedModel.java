package com.mrcrayfish.obfuscate.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * Author: MrCrayfish
 */
public class CustomBipedModel<T extends LivingEntity> extends BipedModel<T>
{
    private BipedModel source;

    public CustomBipedModel(BipedModel<T> source, float modelSize)
    {
        super(modelSize);
        this.source = source;
    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float somethingToDoWithArmSwing, float headYaw, float headPitch)
    {
        super.render(entityIn, limbSwing, limbSwingAmount, somethingToDoWithArmSwing, headYaw, headPitch);
        copyProperties(source.bipedHeadwear, bipedHeadwear);
        copyProperties(source.bipedHead, bipedHead);
        copyProperties(source.bipedBody, bipedBody);
        copyProperties(source.bipedRightArm, bipedRightArm);
        copyProperties(source.bipedLeftArm, bipedLeftArm);
        copyProperties(source.bipedRightLeg, bipedRightLeg);
        copyProperties(source.bipedLeftLeg, bipedLeftLeg);
    }

    private static void copyProperties(ModelRenderer source, ModelRenderer target)
    {
        target.rotateAngleX = source.rotateAngleX;
        target.rotateAngleY = source.rotateAngleY;
        target.rotateAngleZ = source.rotateAngleZ;
        target.rotationPointX = source.rotationPointX;
        target.rotationPointY = source.rotationPointY;
        target.rotationPointZ = source.rotationPointZ;
    }
}
