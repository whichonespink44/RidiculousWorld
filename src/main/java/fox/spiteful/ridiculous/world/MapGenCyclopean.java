package fox.spiteful.ridiculous.world;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenCyclopean extends MapGenStructure
{
    private static int fuck = 0;

    public MapGenCyclopean()
    {
        super();
    }

    public String func_143025_a()
    {
        return "Cyclopean Ruins";
    }

    protected boolean canSpawnStructureAtCoords(int x, int z)
    {
        int k = x >> 4;
        int l = z >> 4;
        this.rand.setSeed((long)(k ^ l << 4) ^ this.worldObj.getSeed());
        //return this.rand.nextInt(3) != 0 ? false : (x != (k << 4) + 4 + this.rand.nextInt(8) ? false : z == (l << 4) + 4 + this.rand.nextInt(8));
        //return true;
        return x % 2 == 0 && z % 2 == 0;
    }

    private int soFuckingRandom(int max){
        Random help = new Random();
        if(++fuck >= 100)
            fuck = 1;
        return ((help.nextInt(rand.nextBoolean() ? 87 : 32) + help.nextInt(rand.nextBoolean() ? 96 : 24) + help.nextInt(rand.nextBoolean() ? 12 : 42)) + rand.nextInt(Math.abs(fuck))) % max;
    }

    protected StructureStart getStructureStart(int x, int z)
    {
        return new MapGenCyclopean.Start(this.worldObj, this.rand, x, z);
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World world, Random rand, int x, int z)
        {
            super(x, z);
            StructureCyclopeanShards.Start start = new StructureCyclopeanShards.Start(rand, (x << 4) + 2, (z << 4) + 2);
            this.components.add(start);
            start.buildComponent(start, this.components, rand);
            ArrayList arraylist = start.field_74967_d;

            while (!arraylist.isEmpty())
            {
                int k = rand.nextInt(arraylist.size());
                StructureComponent structurecomponent = (StructureComponent)arraylist.remove(k);
                structurecomponent.buildComponent(start, this.components, rand);
            }

            this.updateBoundingBox();
            this.setRandomHeight(world, rand, 100, 200);
        }
    }
}